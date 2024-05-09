import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  readonly roleEnum = RoleEnum

  constructor(private router: Router, private authService: AuthService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      const tokenDecoded = this.authService.tokenDecoded();

    return new Observable<boolean>((observer) => {
      if (this.authService.isLogIn) {
        if(tokenDecoded?.scope != this.roleEnum.MEMBER){
          this.router.navigate(['/home']);
        }else{
          this.router.navigate(['/member-page']);

        }
       observer.next(true);
      } else {
        observer.next(true);
      }

    });
  }


}

