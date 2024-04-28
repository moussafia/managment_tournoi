import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rule } from 'src/app/dto/ruleDto/rule';
import { RulesCreateDto } from 'src/app/dto/ruleDto/rulesCreateDto';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  url: string = 'http://localhost:9000/api/v1/rule';
  constructor(private http: HttpClient) { }

  createRule(rule: RulesCreateDto):Observable<any>{
    
    return this.http.post<any>(this.url, rule);
  }
  getAllRule():Observable<Rule[]>{
    return this.http.get<any>(this.url);
  }
}
