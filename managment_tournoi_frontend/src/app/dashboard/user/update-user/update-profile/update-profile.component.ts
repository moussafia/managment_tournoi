import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { UpdateMemberDto } from 'src/app/dto/appUserFileDto/updateMemberDto/updateMemberDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent {


  memberSaveResponse?: DataResponse<MemberSaveResponseDto, any>;
  idMember?: any;
  formUpdateUser?: FormGroup;
  readonly dataState = DataState;
  memberUpdateDto?: UpdateMemberDto;
  fullNameUserUPdate?:string;


  constructor(private route: ActivatedRoute, private fb: FormBuilder, private userService: UserService) { }

  ngOnInit(): void {
     this.route.paramMap.subscribe(params => {
          this.idMember = params.get('id');
    })

    this.userService.getUserById(this.idMember).subscribe({
      next: data => {
        this.formUpdateUser = this.fb.group({
          id: [data.id, Validators.required],
          urlPicture: [data.urlPicture, Validators.required],
          className: [data.className, Validators.required],
          email: [data.email, [Validators.required, Validators.email]],
          lastName: [data.lastName, Validators.required],
          firstName: [data.firstName, Validators.required],
        });
        this.fullNameUserUPdate = data.firstName + ' ' + data.lastName;

      }
    })

  }

  onSubmit() {
    this.formUpdateUser?.markAllAsTouched();
    if(this.formUpdateUser?.invalid) return;
      this.userService.updateMemberProfile(this.formUpdateUser?.value).pipe(
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
