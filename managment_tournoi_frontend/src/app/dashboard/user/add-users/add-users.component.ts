import { Component, ViewChild } from '@angular/core';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { ValidationFieldMaps } from 'src/app/dto/appUserFileDto/createMemberDto/validationFieldMaps';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { UserService } from 'src/app/services/user/user.service';

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

@ViewChild('succesInsertSwal') succesInsertSwal?: SwalComponent;

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
          this.succesInsertSwal?.fire();
          console.log("1" + this.memberFileSaveResponse.data?.message)
          console.log(this.memberFileSaveResponse?.dataState)
        }}
  })
  }
}
