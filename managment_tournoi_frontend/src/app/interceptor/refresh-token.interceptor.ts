import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, concatMap, filter, finalize, take, throwError } from 'rxjs';
import { InterceptorHelper } from './interceptorHelper';
import { AuthService } from '../auth/auth.service';
import { AccesTokenResponse } from '../auth/dto/AccesTokenResponse';

@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {

  isRefreshingToken = false;

  tokenRefreshed$ = new BehaviorSubject<boolean>(false);
  
  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    return next.handle(InterceptorHelper.addTokenToRequest(request, this.authService.jwt)).pipe(
      catchError(err => {
        if (err && err.status === 401) {
          
          return this.handle401Error(request, next);
        }

        return throwError(err);
      })
    );
  }

  private handle401Error(req: HttpRequest<any>, next: HttpHandler): Observable<any> {

    console.log("inside refresh interceptor ")
    if (this.isRefreshingToken) {
      return this.tokenRefreshed$.pipe(
        filter(Boolean),
        take(1),
        concatMap(() => next.handle(InterceptorHelper.addTokenToRequest(req, this.authService.jwt)))
      );
    }

    this.isRefreshingToken = true;
   
    this.tokenRefreshed$.next(false);

    return this.authService.getAccesToken().pipe(
      concatMap((res: AccesTokenResponse) => {

        this.authService.jwt = res.access_Token;

        this.tokenRefreshed$.next(true);
        return next.handle(InterceptorHelper.addTokenToRequest(req, this.authService.jwt));
      }),
      catchError((err) => { console.log("concat not work " + err)
        if (err.status === 401) {
          this.authService.lougout();
        }
        return throwError(err);

      }),
      finalize(() => {
        this.isRefreshingToken = false;
      })
    );
  }
}