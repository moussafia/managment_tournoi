import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DataResponse } from 'src/app/dto/data.state.';
import { ParticipantCreateDto } from 'src/app/dto/participantDto/participantCreateDto';
import { ParticipantUpdateDto } from 'src/app/dto/participantDto/participantUpdateDto';
import { DeleteTeamResponseDto } from 'src/app/dto/teamDto/deleteTeamResponseDto';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  url: string = 'http://localhost:9000/api/v1/team/participent';
  constructor(private http: HttpClient) { }

  createTeam(createTeamDto: ParticipantCreateDto) : Observable<any>{
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    
    const formData = new FormData();

    formData.append('usersIds', createTeamDto.usersIds.toString());
    formData.append('logo', createTeamDto.logo);
    formData.append('numberOfParticipants', createTeamDto.numberOfParticipants.toString());
    formData.append('team', createTeamDto.team);

    return this.http.post<any>(this.url, formData, {headers});
  }

  updateTeam(updateTeamDto: ParticipantUpdateDto) : Observable<any>{
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    
    const formData = new FormData();

    formData.append('usersIds', updateTeamDto.usersIds.toString());
    formData.append('numberOfParticipants', updateTeamDto.numberOfParticipants.toString());
    formData.append('team.id', updateTeamDto.team.id);
    formData.append('team.nameTeam', updateTeamDto.team.nameTeam);
    formData.append('team.logo', updateTeamDto.team.logo);
    formData.append('team.logoPublicId', updateTeamDto.team.logoPublicId);

    if(updateTeamDto.logo){
      formData.append('logo', updateTeamDto.logo);
    }

    return this.http.put<any>(this.url, formData, {headers});
  }

  deleteTeam(id:any,publicIdLogo: string): Observable<DataResponse<DeleteTeamResponseDto, any>> {   
    return this.http.delete<any>(`${this.url}?id=${id}&publicIdLogo=${publicIdLogo}`);
  }

}
