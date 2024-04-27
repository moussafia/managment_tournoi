import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { TeamShowDto } from 'src/app/dto/teamDto/teamShowDto';
import { TeamService } from 'src/app/services/team/team.service';

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

  constructor(private teamService: TeamService){}
 
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
}
