import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { catchError, map, of, startWith } from 'rxjs';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { LevelEnum } from 'src/app/dto/enum/levelEnum';
import { MatchCreateDto } from 'src/app/dto/matchDto/matchCreateDto';
import { MatchShowDto } from 'src/app/dto/matchDto/matchShowDto';
import { MatchTeamCreateDto } from 'src/app/dto/matchTeamDto/matchTeamCreateDto';
import { TeamShowDto } from 'src/app/dto/teamDto/teamShowDto';
import { MatchService } from 'src/app/services/match/match.service';
import { TeamService } from 'src/app/services/team/team.service';
import { UserService } from 'src/app/services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-match',
  templateUrl: './add-match.component.html',
  styleUrls: ['./add-match.component.css']
})
export class AddMatchComponent implements OnInit {

  showResults: boolean = false;
  selectedUser?: {id:string, name: string, url: string};
  memberShowDto?: MemberShowDto[];
  participantId?: string;
  readonly dataState = DataState;
  levelEnum = Object.keys(LevelEnum);
  teamShowDto?: TeamShowDto[];
  matchCreateDto?: MatchTeamCreateDto;  
  teamIds: any[]=["0", "0"];
  errorMessage: any;
  dateMatch: any;
  stratTimeMatch: any;
  endTimeMatch: any;
  selectedLevel: any;
  match?: MatchCreateDto;
  matchSaveSuccessResponse?: DataResponse<MatchShowDto, any>;


  @ViewChild('inputSearch') inputSearch?: ElementRef;
  @ViewChild('formAddMatch') formAddMatch?: NgForm;
  
  constructor(private userService : UserService, private teamService: TeamService, private matchService: MatchService){}

  ngOnInit(): void {
    this.getLatestTeam();
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

    this.selectedUser={id: id,name: name,url: url};
    this.showResults = false;

    if (this.inputSearch && this.inputSearch.nativeElement) {
      
      this.inputSearch.nativeElement.value = '';
    } 

  
   }


  deleteSelectedUser(id: any) {
    console.log(id)
    this.selectedUser = undefined;

  }

  getLatestTeam():void{
      this.teamService.getLatestTeam()
       .subscribe({
        next: data => {
          this.teamShowDto = data
        }
      })
  }

  selectTeam1(event: any){
    const existingId = this.teamIds.find(teamId => teamId === event.target.value);
    if(!existingId){
      this.teamIds[0] = event.target.value;
    } else this.errorMessage = 'value already selected'
  }
  selectTeam2(event: any){
    const existingId = this.teamIds.find(teamId => teamId === event.target.value);
    if(!existingId)
      this.teamIds[1] = event.target.value;
    else this.errorMessage = 'value already selected'
  }
  onSubmit() {
    this.match = {
        date: this.dateMatch,
        startDateMatch: this.stratTimeMatch,
        endDateMatch:  this.endTimeMatch
    }

    this.matchCreateDto ={
      match: this.match ,
      teamId: this.teamIds ,
      arbitraire: this.selectedUser?.id,
      levelEnum: this.selectedLevel 
  }
  this.matchService.createMatchTeam(this.matchCreateDto).pipe(
    map(data=>{
      return ({dataState: this.dataState.LOADED, data: data || []}); 
    }),
    startWith({dataState: this.dataState.LOADING}),
    catchError(error => of({dataState: this.dataState.ERROR, error: error.error.message }))
  ).subscribe({
    next: data => {
      this.matchSaveSuccessResponse = data as DataResponse<MatchShowDto, any>
      if(data.dataState === this.dataState.LOADED){
        this.swalSucces('match is created with Succes');
        //clear form
        this.formAddMatch?.resetForm();
        this.teamIds =["0", "0"];
        this.errorMessage ='';
        this.dateMatch='';
        this.stratTimeMatch='';
        this.endTimeMatch='';
        this.selectedLevel='';
        this.selectedUser = undefined;

      }else if(data.dataState === this.dataState.ERROR){

          this.swalError(this.matchSaveSuccessResponse.error);
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
