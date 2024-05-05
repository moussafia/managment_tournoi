import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationRequestDto } from './dto/AuthenticationRequestDto';
import { Observable, map, tap } from 'rxjs';
import { DataResponse } from '../dto/data.state.';
import { AuthenticationResponseDto } from './dto/AuthenticationResponseDto';
import { MemberShowDto } from '../dto/appUserFileDto/getDto/memberShowDto';
import { TokenDecoced } from './dto/TokenDecoded';
import { jwtDecode } from 'jwt-decode';
import { AccessTokenRequestDto } from './dto/AccessTokenRequestDto';
import { AccesTokenResponse } from './dto/AccesTokenResponse';
import { LogoutResponseDto } from './dto/LogoutResponseDto';

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

   set jwt(value: string){
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


  tokenDecoded(): TokenDecoced | null{
    try{
     return jwtDecode(this.jwt);
    }catch(Error){
      return null;
    }
  }


  signIn(authDto: AuthenticationRequestDto): Observable<DataResponse<AuthenticationResponseDto, any>>{

    return this.http.post<DataResponse<AuthenticationResponseDto, any>>(`${this.url}/logIn`, authDto);
  }

  me(): Observable<MemberShowDto>{

    return this.http.get<MemberShowDto>(`${this.url}/me`);

  }

  getAccesToken():Observable<AccesTokenResponse>{

    return this.http.post<AccesTokenResponse>(`${this.http}/token`,{refreshToken: this.refreshToken});
  }


  lougout():Observable<LogoutResponseDto>{
    
    return this.http.post<LogoutResponseDto>(`${this.http}/logout`,{refreshToken :  this.refreshToken});
  }
}
