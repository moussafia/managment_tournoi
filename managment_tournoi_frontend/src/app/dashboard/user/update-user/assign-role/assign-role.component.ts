import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';
import { AssignRoleDto } from 'src/app/dto/roleDto/assignRoleDto';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-assign-role',
  templateUrl: './assign-role.component.html',
  styleUrls: ['./assign-role.component.css']
})
export class AssignRoleComponent implements OnInit {
  memberShowDto?: DataResponse<MemberShowDto, any>;
  idMember?: any;
  formAssignRole?: FormGroup;
  readonly dataState = DataState;
  roleEnumValues = Object.values(RoleEnum);
  assignRoleDto?: AssignRoleDto;
  fullNameUserUPdate?:string;


  constructor(private route: ActivatedRoute, private fb: FormBuilder, private userService: UserService) { }

  ngOnInit(): void {
     this.route.paramMap.subscribe(params => {
          this.idMember = params.get('id');
    })

    this.userService.getUserById(this.idMember).subscribe({
      next: data => {
        this.formAssignRole = this.fb.group({
          memberId: [data.id, Validators.required],
          roleName: [data.role.name, Validators.required],
        });
        this.fullNameUserUPdate = data.firstName + ' ' + data.lastName;

      }
    })

  }

  onSubmit() {
    this.formAssignRole?.markAllAsTouched();
    if(this.formAssignRole?.invalid) return;
      this.userService.assignRole(this.formAssignRole?.value).pipe(
        map(data=>{
          return ({dataState: this.dataState.LOADED, data: data || []}); 
        }),
        startWith({dataState: this.dataState.LOADING}),
        catchError(error => of({dataState: this.dataState.ERROR, error: error.error }))
      ).subscribe({
        next: data => {
          this.memberShowDto = data as DataResponse<MemberShowDto, any>
          console.log(data)
          if(data.dataState === this.dataState.LOADED){
            this.swalSucces("role updated with succes");
          }else if(data.dataState === this.dataState.ERROR){
            let textError = this.generateTextError(this.memberShowDto);
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
    
     generateTextError(memberSaveResponse : DataResponse<MemberShowDto, any>): string{
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
