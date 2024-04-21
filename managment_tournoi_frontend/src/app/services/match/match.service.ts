import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatchShowDto } from 'src/app/dto/matchDto/matchShowDto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient) { }

  private url : string = 'http://localhost:9000/api/v1/match';

  getMatchToday() : Observable<MatchShowDto> {
       return this.http.get<MatchShowDto>(this.url);
  }
}
