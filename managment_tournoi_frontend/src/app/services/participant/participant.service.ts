import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParticipantCreateDto } from 'src/app/dto/participantDto/participantCreateDto';

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
}
