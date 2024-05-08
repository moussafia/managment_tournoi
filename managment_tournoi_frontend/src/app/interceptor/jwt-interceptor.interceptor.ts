import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, from, mergeMap, switchMap, tap } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { TokenDecoced } from '../auth/dto/TokenDecoded';

@Injectable()
export class JwtInterceptorInterceptor implements HttpInterceptor {

  jwtDecoded: TokenDecoced | null=null

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    console.log(request.url)
    const isLogIn = this.authService.isLogIn;

    if(isLogIn){

      this.jwtDecoded = this.authService.tokenDecoded(); 

      if(request.url.endsWith("logIn") || !this.jwtDecoded)
          return next.handle(request);
        
     
      if(this.isTokenExpired(this.jwtDecoded)){


        request = this.addTokenToRequest(request, this.authService.jwt);

        console.log('token from interc ' + this.authService.jwt)

        return next.handle(request);

      }else{  
        console.log('token is Expired ')
        console.log(request.url)
         return this.authService.getAccesToken().pipe(
            switchMap((data)=>{ 
              console.log('inside switch map ---------------------')
                this.authService.jwt = data.access_token;
                console.log('refreshToken ' + data)
                const cloned = this.addTokenToRequest(request, this.authService.jwt);
                return next.handle(cloned);
              }))
          
            }
    }

    return next.handle(request);
  }




  private addTokenToRequest(request: HttpRequest<any>, token: string): HttpRequest<any> {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  private isTokenExpired(jwtDecoded :TokenDecoced): boolean{
    const tokenExp = jwtDecoded.exp;
    const tokenExpDate = new Date(tokenExp * 1000);
    const dateNow = new Date();
    return tokenExpDate > dateNow;
  }
}
