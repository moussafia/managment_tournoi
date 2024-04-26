import { HttpClient } from '@angular/common/http';
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
    
    return this.http.post<any>(this.url, createTeamDto);
  }
}
