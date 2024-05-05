import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, from, mergeMap } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { TokenDecoced } from '../auth/dto/TokenDecoded';

@Injectable()
export class JwtInterceptorInterceptor implements HttpInterceptor {

  jwtDecoded: TokenDecoced | null=null

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const isLogIn = this.authService.isLogIn;
    if(isLogIn){

      this.jwtDecoded = this.authService.tokenDecoded(); 

      console.log("jwt decoded " + this.jwtDecoded)

      if(this.jwtDecoded){

        const tokenExp = this.jwtDecoded.exp;
        const tokenExpDate = new Date(tokenExp * 1000);
        const dateNow = new Date();

        if(tokenExpDate > dateNow){

          request = this.addTokenToRequest(request, this.authService.jwt);
          return next.handle(request);


        }else{

          return from(this.authService.getAccesToken().pipe(
            mergeMap((data)=>{
              console.log("acces token from interceptor " + data.access_token);
                this.authService.jwt = data.access_token;
                const cloned = this.addTokenToRequest(request, this.authService.jwt);
                return next.handle(cloned);
              })
          ))
        }
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
}
