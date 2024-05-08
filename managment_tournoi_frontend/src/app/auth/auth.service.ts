import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationRequestDto } from './dto/AuthenticationRequestDto';
import { Observable } from 'rxjs';
import { DataResponse } from '../dto/data.state.';
import { AuthenticationResponseDto } from './dto/AuthenticationResponseDto';
import { MemberShowDto } from '../dto/appUserFileDto/getDto/memberShowDto';
import { TokenDecoced } from './dto/TokenDecoded';
import { jwtDecode } from 'jwt-decode';
import { AccesTokenResponse } from './dto/AccesTokenResponse';
import { LogoutResponseDto } from './dto/LogoutResponseDto';
import { AccessTokenRequestDto } from './dto/AccessTokenRequestDto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private static readonly _jwt_key="accessToken";
  private static readonly _jwtRefresh_key="refreshToken";

  accessTokenRequest?: AccessTokenRequestDto;

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
    return this.http.post<AccesTokenResponse>(`${this.url}/token`,{refreshToken: this.refreshToken});
  }


  lougout():Observable<LogoutResponseDto>{

    this.accessTokenRequest = {refreshToken :  this.refreshToken};

   const result =  this.http.post<LogoutResponseDto>(`${this.http}/logout`,this.accessTokenRequest);
   localStorage.clear();
   return result;
  }



}
