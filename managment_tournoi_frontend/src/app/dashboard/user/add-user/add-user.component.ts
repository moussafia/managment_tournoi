import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

formAddUser?: FormGroup;
readonly dataState = DataState;

memberSaveResponse?: DataResponse<MemberSaveResponseDto, any>

  constructor(private fb: FormBuilder, private userService: UserService){
  }
  ngOnInit(): void {
    this.formAddUser = this.fb.group({
      urlPicture:['',Validators.required],
      className: ['',Validators.required],
      email: ['',[Validators.required, Validators.email]],
      lastName: ['',Validators.required],
      firstName: ['',Validators.required],
    })
  }
  onSubmit() {
    this.formAddUser?.markAllAsTouched();
    if(this.formAddUser?.invalid) return;
      this.userService.createSingleUser(this.formAddUser?.value).pipe(
        map(data=>{
          return ({dataState: this.dataState.LOADED, data: data || []}); 
        }),
        startWith({dataState: this.dataState.LOADING}),
        catchError(error => of({dataState: this.dataState.ERROR, error: error.error }))
      ).subscribe({
        next: data => {
          this.memberSaveResponse = data as DataResponse<MemberSaveResponseDto, any>
          if(data.dataState === this.dataState.LOADED){
            this.swalSucces(this.memberSaveResponse?.data?.message);
            this.formAddUser?.reset();
          }else if(data.dataState === this.dataState.ERROR){
            let textError = this.generateTextError(this.memberSaveResponse);
            this.swalError(textError);
          }

        }
      })
  }
  swalError(textError: string | undefined){
    Swal.fire({
      icon: "error",
      title: "Oops...",
      html: textError,
    });
}

 generateTextError(memberSaveResponse : DataResponse<MemberSaveResponseDto, any>): string{
  let fields  = memberSaveResponse?.error;
  let textError = ``;
  for (const key of Object.keys(fields|| {})) {
    let field = fields[key];
        textError += `<div class="p-2 mb-2 rounded">`;
        textError += `<div class="font-bold mb-1 text-md">field shoud be corected <span class='text-red-500'>${key}</span> :</div>`;
        textError += `<ul class="list-disc flex flex-col justify-start items-start">`;
        textError += `<li class='py-2'> - ${field}</li>`;
        textError += `</ul>`;
        textError += `</div>`;
  }
  return textError;
}
  swalSucces(message: string | undefined){
    Swal.fire({
      position : "center",
      icon :"success",
      title : message,
      showConfirmButton : false,
      timer : 3500 
    })
}
}
