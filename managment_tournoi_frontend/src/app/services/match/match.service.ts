import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatchShowDto } from 'src/app/dto/matchDto/matchShowDto';
import { Observable } from 'rxjs';
import { DataResponse } from 'src/app/dto/data.state.';
import { MatchTeamCreateDto } from 'src/app/dto/matchTeamDto/matchTeamCreateDto';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient) { }

  private url : string = 'http://localhost:9000/api/v1';

  getMatchToday() : Observable<MatchShowDto> {
       return this.http.get<MatchShowDto>(`${this.url}/match`);
  }
  createMatchTeam(matchCreateDto: MatchTeamCreateDto) : Observable<DataResponse<MatchShowDto,any>> {
    console.log(matchCreateDto)
    return this.http.post<DataResponse<MatchShowDto,any>>(`${this.url}/matchTeam`, matchCreateDto);
}
}
