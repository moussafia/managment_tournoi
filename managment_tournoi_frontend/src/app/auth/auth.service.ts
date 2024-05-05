import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationRequestDto } from './dto/AuthenticationRequestDto';
import { Observable, tap } from 'rxjs';
import { DataResponse } from '../dto/data.state.';
import { AuthenticationResponseDto } from './dto/AuthenticationResponseDto';
import { MemberShowDto } from '../dto/appUserFileDto/getDto/memberShowDto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private static readonly _jwt_key="accessToken";
  private static readonly _jwtRefresh_key="refreshToken";

  url: string = 'http://localhost:9000/api/v1/auth';

  constructor(private http: HttpClient) { }


  get jwt(): string{
    return localStorage.getItem(AuthService._jwt_key) ?? '';
  }

  private set jwt(value: string){
     localStorage.setItem(AuthService._jwt_key,value);
  }

  get isLogIn(): boolean{
    return !!this.jwt; 
  }

  get refreshToken():string{
    return localStorage.getItem(AuthService._jwtRefresh_key) ?? ''
  }
  set refreshToken(value: string) {
    localStorage.setItem(AuthService._jwtRefresh_key, value);
  }

  signIn(authDto: AuthenticationRequestDto): Observable<AuthenticationResponseDto>{

    return this.http.post<AuthenticationResponseDto>(`${this.url}/logIn`, authDto)
    .pipe(
      tap(data=>{
          this.jwt = data.accessToken;
          this.refreshToken = data.refreshToken;
     
      })
    );
  }

  me(): Observable<MemberShowDto>{

    return this.http.get<MemberShowDto>(`${this.url}/me`);

  }

}
