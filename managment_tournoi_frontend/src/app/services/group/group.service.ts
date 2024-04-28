import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DataResponse } from 'src/app/dto/data.state.';
import { GroupShowDto } from 'src/app/dto/groupDto/groupShowDto';
import { DeleteTeamResponseDto } from 'src/app/dto/teamDto/deleteTeamResponseDto';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  url: string= 'http://localhost:9000/api/v1/teamGroup';

  constructor(private http: HttpClient) { }

  getAllGroup(): Observable<any>{
    return this.http.get<any>(this.url);
  }

  createRandomGrp(): Observable<DataResponse<GroupShowDto[], any>>{
    return this.http.post<DataResponse<GroupShowDto[], any>>(this.url, {} );
  }

  deleteTeamGrp(): Observable<DataResponse<DeleteTeamResponseDto, any>>{
    return this.http.delete<DataResponse<DeleteTeamResponseDto, any>>(this.url);
  }
}
