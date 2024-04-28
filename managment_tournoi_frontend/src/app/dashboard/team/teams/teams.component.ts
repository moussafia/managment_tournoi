import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { catchError, map, of, startWith, throwError } from 'rxjs';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { DeleteTeamResponseDto } from 'src/app/dto/teamDto/deleteTeamResponseDto';
import { TeamShowDto } from 'src/app/dto/teamDto/teamShowDto';
import { ParticipantService } from 'src/app/services/participant/participant.service';
import { TeamService } from 'src/app/services/team/team.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {


  teamShowDto?: TeamShowDto[];
  pageSize: number = 3;
  pageIndex: number = 0;
  totalItems: number = 0;
  selectedOption: string = 'all';

  readonly dataState = DataState;
  teamDeleteResponseDto?: DataResponse<DeleteTeamResponseDto, any>;

  constructor(private teamService: TeamService, private router: Router, private partticipantService: ParticipantService){}
 
  ngOnInit(): void {
    this.loadTeams();
    
  }


updateUser(arg0: any) {

}
onPageChange(event: PageEvent): void {
  this.pageIndex = event.pageIndex;
  this.pageSize = event.pageSize;
  this.loadTeams();
}
loadTeams(): void {
  this.teamService.getAllTeams(this.pageIndex, this.pageSize)
    .subscribe({
      next: data => {
        this.teamShowDto = data.content
        this.totalItems = data.totalElements
      }
    })
}
filterTeams():void{
  if(this.selectedOption == "latest"){
    this.teamService.getLatestTeam()
     .subscribe({
      next: data => {
        this.teamShowDto = data
      }
    })
  } else {
    this.loadTeams();
  }
}
navigateToUpdatePage(id:any){
  this.router.navigateByUrl('/team/update-team/' + id);
}
deleteTeam(idTeam: any, idPubLog: string, nameTeam: string) {
  
  Swal.fire({
    title: 'Are you sure?',
    text: 'Do you wanna delete team with name ' + nameTeam,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
        this.partticipantService.deleteTeam(idTeam, idPubLog)
        .pipe(
          map(data => {
            return ({dataState: this.dataState.LOADED, data: data}); 
          }),
          startWith({dataState: this.dataState.LOADING})
        )
        .subscribe({
          next: data => {
            this.teamDeleteResponseDto = data as DataResponse<DeleteTeamResponseDto, any>;
            console.log(data)
            if(data.dataState == this.dataState.LOADED){
                this.swalSuccess(this.teamDeleteResponseDto.data?.message)
                this.loadTeams();
            } 
          }
        })
    }
  });
}

swalSuccess(message: any){
    Swal.fire({
      position : "center",
      icon :"success",
      title : message,
      showConfirmButton : false,
      timer : 3500 
    })
}


}
