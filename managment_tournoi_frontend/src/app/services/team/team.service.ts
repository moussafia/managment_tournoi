import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  url: string = 'http://localhost:9000/api/v1/team'
  constructor(private http: HttpClient) { }


  getAllTeams(page: number, size: number):Observable<any>{
    return this.http.get<any>( `${this.url}/edit?page=${page}&size=${size}`);
  }

  getLatestTeam(): Observable<any>{
    return this.http.get<any>( `${this.url}/edit/latest`);
  }
  
  getTeamById(id: any): Observable<any>{
    return this.http.get<any>( `${this.url}/edit/${id}`);
  }

}
