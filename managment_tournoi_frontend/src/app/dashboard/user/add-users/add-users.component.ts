import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { ValidationFieldMaps } from 'src/app/dto/appUserFileDto/createMemberDto/validationFieldMaps';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-users',
  templateUrl: './add-users.component.html',
  styleUrls: ['./add-users.component.css']
})
export class AddUsersComponent {

memberFile: File | null = null;
selectedFile: File | null = null;
memberFileSaveResponse?: DataResponse<MemberSaveResponseDto, ValidationFieldMaps>

readonly dataState = DataState;

@ViewChild('formAddUsersXlsx') formAddUsersXlsx?: NgForm;

constructor(private userService: UserService){}

downloadXlsxModal() {
  this.userService.downloadModalXlsx();
}

onFileSelected(event: Event) {
  const inputElement = event.target as HTMLInputElement;
  if (inputElement.files && inputElement.files.length > 0) {
    this.selectedFile = inputElement.files[0];
  }
}
addUsers() {
  this.userService.createMembersFromXlsx(this.selectedFile).pipe(
    map(data=>{
      return ({dataState: this.dataState.LOADED, data: data || []}); 
    }),
    startWith({dataState: this.dataState.LOADING}),
    catchError(error => of({dataState: this.dataState.ERROR, error: {message : error.error.message, field: error.error.validationFieldMaps} }))
  ).subscribe({
    next: data =>{
        this.memberFileSaveResponse = data as DataResponse<MemberSaveResponseDto, any>
        if (data.dataState === this.dataState.LOADED ) {
            this.swalSucces(this.memberFileSaveResponse?.data?.message);
            this.formAddUsersXlsx?.reset();
            const fileInput = document.querySelector('input[type="file"]');
        } else if( data.dataState === this.dataState.ERROR ){
          let messageError = this.memberFileSaveResponse?.error?.['message'];
          let fields  = this.memberFileSaveResponse?.error?.['field'];
          let textError = `<h1 class='text-lg text-red-700 text-center my-4'>${messageError}</h1>`;
          for(const key in fields){           
                let fieldsArray = fields[parseInt(key)];
                textError += `<div class="p-2 mb-2 rounded">`;
                textError += `<div class="font-bold mb-1 text-md">row number ${key} in excel :</div>`;
                textError += `<ul class="list-disc flex flex-col justify-start items-start">`;
                for(let g = 0; g < fieldsArray.length; g++){
                  textError += `<li class='py-2'> - ${fieldsArray[g]}</li>`;
                }
                textError += `</ul>`;
                textError += `</div>`;
          }
              Swal.fire({
                icon: "error",
                title: "Oops...",
                html: textError,
              });
        }
      }
        
  })
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
