import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { TokenDecoced } from '../auth/dto/TokenDecoded';
import { InterceptorHelper } from './interceptorHelper';

@Injectable()
export class AccessTokenInterceptor implements HttpInterceptor {

  jwtDecoded: TokenDecoced | null=null;

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const isLogIn = this.authService.isLogIn;

    if(isLogIn){

    this.jwtDecoded = this.authService.tokenDecoded(); 

    if(request.url.endsWith("logIn") || !this.jwtDecoded)
          return next.handle(request);
        
     
    if(InterceptorHelper.isTokenExpired(this.jwtDecoded)){


    request = InterceptorHelper.addTokenToRequest(request, this.authService.jwt);

    console.log('token from interc ' + this.authService.jwt)

    return next.handle(request);
  }
}

    return next.handle(request);

  }

}