import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { ParticipantCreateDto } from 'src/app/dto/participantDto/participantCreateDto';
import { TeamShowDto } from 'src/app/dto/teamDto/teamShowDto';
import { ParticipantService } from 'src/app/services/participant/participant.service';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent {

  showResults: boolean = false;
  selectedUser: {id:string, name: string, url: string}[]= [];
  memberShowDto?: MemberShowDto[];
  createTeamDto?: ParticipantCreateDto;
  team?: string;
  numberOfParticipants?: number;
  logo?: File;
  participantId?: string[];
  showUrlPic: File | null = null
  readonly dataState = DataState
  teamSaveResponse?: DataResponse<TeamShowDto, any>;
  @ViewChild('formAddTeam') formAddTeam?: NgForm;
  @ViewChild('inputSearch') inputSearch?: ElementRef;
  
  constructor(private userService : UserService, private participantService: ParticipantService){}

  

  searchMemberByName(event: any) {
    this.showResults = true
    const keywordSearch = event.target.value;
      this.userService.searchMember(keywordSearch, 0, 2).subscribe({
        next: data => this.memberShowDto = data.content
      })
  }

  handleClickOutside(){
    this.showResults = false
  }

  selectParticipant(id: string, name: string, url: string){
    const existingid = this.selectedUser.find(user => user.id === id);
    if(!existingid){
      this.selectedUser?.push({id: id,name: name,url: url});
    }
    this.showResults = false;

    if (this.inputSearch && this.inputSearch.nativeElement) {
      
      this.inputSearch.nativeElement.value = '';
    } 

  
   }


  deleteSelectedUser(id: string) {
    console.log(id)
    const indexUser = this.selectedUser.findIndex(user => user.id === id);
    if(indexUser != -1){
        this.selectedUser.splice(indexUser, 1);
    }
  }


  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.logo = inputElement.files[0];
    }
  }


  onSubmit() {
    if (!this.team || !this.logo || !this.numberOfParticipants) 
      return;

    this.participantId = this.selectedUser.map(user => user.id);
    this.createTeamDto = {
      usersIds: this.participantId,
      team: this.team,
      logo: this.logo,
      numberOfParticipants: this.numberOfParticipants
    }
    this.participantService.createTeam(this.createTeamDto).pipe(
      map(data=>{
        return ({dataState: this.dataState.LOADED, data: data || []}); 
      }),
      startWith({dataState: this.dataState.LOADING}),
      catchError(error => of({dataState: this.dataState.ERROR, error: error.error.message }))
    ).subscribe({
      next: data => {
        this.teamSaveResponse = data as DataResponse<TeamShowDto, any>
        if(data.dataState === this.dataState.LOADED){
          this.swalSucces('team with name ' + this.teamSaveResponse?.data?.nameTeam + ' is created with Succes');
          //clear form
          this.formAddTeam?.resetForm();
          //clear selected user
          this.participantId?.splice(0, this.participantId.length);
          this.selectedUser = [];

        }else if(data.dataState === this.dataState.ERROR){

            this.swalError(this.teamSaveResponse.error);
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
  
      

