import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { PasswordRequestUpdateDto } from 'src/app/dto/appUserFileDto/updateMemberDto/passwordRequestUpdateDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {
  
  memberSaveResponse?: DataResponse<MemberSaveResponseDto, any>;
  idMember?: any;
  formUpdatePasswordUser?: FormGroup;
  readonly dataState = DataState;
  memberUpdatePasswordDto?: PasswordRequestUpdateDto;
  fullNameUserUPdate?:string;

  constructor(private route: ActivatedRoute, private fb: FormBuilder, private userService: UserService) { }

  ngOnInit(): void {
     this.route.paramMap.subscribe(params => {
          this.idMember = params.get('id');
    })

    this.userService.getUserById(this.idMember).subscribe({
      next: data => {
        this.formUpdatePasswordUser = this.fb.group({
          memberId: [data.id, Validators.required],
          oldPassword: ['', Validators.required],
          newPassword: ['', Validators.required],
          confirmNewPassword: ['', Validators.required]
        });
        this.fullNameUserUPdate = data.firstName + ' ' + data.lastName;
      }
    })

  }

  onSubmit() {
    this.formUpdatePasswordUser?.markAllAsTouched();
    if(this.formUpdatePasswordUser?.invalid) return;
      this.userService.updateMemberPassword(this.formUpdatePasswordUser?.value).pipe(
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
            let textError  = this.memberSaveResponse?.error?.message;
            this.swalError(textError);
          }

        }
      })  
  }

      swalError(textError: string | undefined){
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: textError,
        });
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
