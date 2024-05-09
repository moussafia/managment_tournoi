import { Component, OnInit } from '@angular/core';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { GroupShowDto } from 'src/app/dto/groupDto/groupShowDto';
import { GroupService } from 'src/app/services/group/group.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  groupResponseCreateDto?: DataResponse<GroupShowDto[], any>;
  readonly dataState = DataState;

  constructor(private groupService: GroupService){}

  ngOnInit(): void {
    this.loadGroups();
  }

  loadGroups(){
    this.groupService.getAllGroup().subscribe({
      next: (data: GroupShowDto[]) =>{
        console.log(data)
        if(data.length > 0){
          this.groupResponseCreateDto = {dataState: this.dataState.LOADED, data: data}
        }else{
          this.groupResponseCreateDto = {dataState: this.dataState.LOADED, data: []}
        }
      }
    })
  }
}
