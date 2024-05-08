import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { TokenDecoced } from '../dto/TokenDecoded';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  jwtDecoded: TokenDecoced | null =null;

  constructor(private router: Router, private authService: AuthService) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      return new Observable<boolean>((observer)=>{
        if(this.checkIfALreadyLogIn()){

          this.router.navigate(['/home']);
          return observer.next(false);

      }
      else return observer.next(true);
      })

      
  }


  private checkIfALreadyLogIn(): boolean{

    this.jwtDecoded = this.authService.tokenDecoded();

    if(!this.jwtDecoded)
      return false;
    
    return this.authService.isLogIn && (this.jwtDecoded.iss == "Y.soccer-service");
  }
  
}
