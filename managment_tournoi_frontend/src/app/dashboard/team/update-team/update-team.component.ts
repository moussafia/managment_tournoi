import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { ParticipantUpdateDto } from 'src/app/dto/participantDto/participantUpdateDto';
import { TeamShowDto } from 'src/app/dto/teamDto/teamShowDto';
import { TeamUpdateDto } from 'src/app/dto/teamDto/teamUpdateDto';
import { ParticipantService } from 'src/app/services/participant/participant.service';
import { TeamService } from 'src/app/services/team/team.service';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-team',
  templateUrl: './update-team.component.html',
  styleUrls: ['./update-team.component.css']
})
export class UpdateTeamComponent implements OnInit {

  idTeam: any;
  teamShowDto?: TeamShowDto;
  showResults: boolean = false;
  selectedUser: {id:string, name: string, url: string}[]= [];
  memberShowDto?: MemberShowDto[];
  updateTeamDto?: ParticipantUpdateDto;
  team?: TeamUpdateDto;
  numberOfParticipants?: number;
  participantId?: string[];
  showUrlPic: File | null = null;
  logo?:File;
  readonly dataState = DataState
  teamSaveResponse?: DataResponse<TeamShowDto, any>;
  @ViewChild('formUpdateTeam') formUpdateTeam?: NgForm;
  @ViewChild('inputSearch') inputSearch?: ElementRef;
  
  constructor(private route: ActivatedRoute, private teamService: TeamService, private userService : UserService, private participantService: ParticipantService){}

  ngOnInit(): void {
    this.route.params.subscribe( params => {
      this.idTeam = params['id']
    })

      this.teamService.getTeamById(this.idTeam).subscribe({
        next: data => {
          this.teamShowDto = data
          this.numberOfParticipants = this.teamShowDto?.participants.length;
          if(this.teamShowDto){
              this.team = { id: this.teamShowDto?.id, nameTeam: this.teamShowDto?.nameTeam, logo: this.teamShowDto.logo ,logoPublicId: this.teamShowDto?.logoPublicId};
          }
        this.teamShowDto?.participants.forEach(team => {
          this.selectedUser.push({id:team.user.id, name: team.user.firstName + ' ' + team.user.lastName, url: team.user.urlPicture})
          this.participantId?.push(team.user.id)
        })

      }
    })
  }

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
      if (inputElement.files && inputElement.files.length > 0 && this.team) {
        this.logo = inputElement.files[0];
      }
    }


  onSubmit() {
    if (!this.team || !this.numberOfParticipants) 
      return;

    this.participantId = this.selectedUser.map(user => user.id);
    this.updateTeamDto = {
      usersIds: this.participantId,
      team: this.team,
      numberOfParticipants: this.numberOfParticipants,
      logo: this.logo 
    }
    this.participantService.updateTeam(this.updateTeamDto).pipe(
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
