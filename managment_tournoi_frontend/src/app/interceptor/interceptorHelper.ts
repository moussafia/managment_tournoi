import { HttpRequest } from "@angular/common/http";
import { TokenDecoced } from "../auth/dto/TokenDecoded";

export class InterceptorHelper{


    public static addTokenToRequest(request: HttpRequest<any>, token: string): HttpRequest<any> {
        if(!request.url.endsWith('token') && !request.url.endsWith('logIn')){
            return request.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
                });
        }else return request;
      }
  
    public static isTokenExpired(jwtDecoded :TokenDecoced): boolean{
    const tokenExp = jwtDecoded.exp;
    const tokenExpDate = new Date(tokenExp * 1000);
    const dateNow = new Date();
    return tokenExpDate > dateNow;
    }
}