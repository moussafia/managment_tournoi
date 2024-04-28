import { Component, OnInit } from '@angular/core';
import { catchError, map, of, startWith } from 'rxjs';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { GroupShowDto } from 'src/app/dto/groupDto/groupShowDto';
import { DeleteTeamResponseDto } from 'src/app/dto/teamDto/deleteTeamResponseDto';
import { GroupService } from 'src/app/services/group/group.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  groupResponseCreateDto?: DataResponse<GroupShowDto[], any>;
  groupResponseDeleteDto?: DataResponse<DeleteTeamResponseDto, any>;
  readonly dataState = DataState;

  constructor(private groupService: GroupService){}

  ngOnInit(): void {
    this.loadGroups();
  }

  loadGroups(){
    this.groupService.getAllGroup().subscribe({
      next: data =>{
        if(data.length > 0){
          this.groupResponseCreateDto = {dataState: this.dataState.LOADED, data: data}
        }else{
          this.groupResponseCreateDto = {dataState: this.dataState.LOADED, data: []}
        }
      }
    })
  }

  createRandomGroup(){
      this.groupService.createRandomGrp().pipe(
        map(data=>{
          return ({dataState: this.dataState.LOADED, data: data}); 
        }),
        startWith({dataState: this.dataState.LOADING}),
        catchError(error => of({dataState: this.dataState.ERROR, error: error.error.message }))
      ).subscribe({
        next: data => { console.log(data)
          this.groupResponseCreateDto = data as DataResponse<GroupShowDto[], any>
          if(data.dataState === this.dataState.LOADED){
            this.swalSuccess('Group is created with Succes');
  
          }else if(data.dataState === this.dataState.ERROR){
              this.swalError(this.groupResponseCreateDto.error);
          }
  
        }
      })
    }
    deleteAllGroup(){
      Swal.fire({
        title: 'Are you sure?',
        text: 'Do you wanna delete all groups',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
            this.groupService.deleteTeamGrp()
            .pipe(
              map(data => {
                return ({dataState: this.dataState.LOADED, data: data}); 
              }),
              startWith({dataState: this.dataState.LOADING})
            )
            .subscribe({
              next: data => {
                this.groupResponseDeleteDto = data as DataResponse<DeleteTeamResponseDto, any>;
                if(data.dataState == this.dataState.LOADED){
                    this.swalSuccess(this.groupResponseDeleteDto.data?.message)
                    this.loadGroups();
                } 
              }
            })
        }
      });
    }
  
    swalError(textError: string | undefined){
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: textError,
      });
    }
  
  
    swalSuccess(message: string | undefined){
      Swal.fire({
        position : "center",
        icon :"success",
        title : message,
        showConfirmButton : false,
        timer : 3500 
      })
    }
}
