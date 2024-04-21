import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  private url:string = 'http://localhost:9000/api/v1/member'
  
  getAllMembers(page: number, size: number):Observable<any>{
    return this.http.get<any>( `${this.url}?page=${page}&size=${size}`)
  }
}
