import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';
import { GuardHelper } from './gard.helper';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  readonly roleEnum = RoleEnum;
  constructor(private guardHelper: GuardHelper){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const admin = this.roleEnum.ADMIN;
    return this.guardHelper.checkAccesPagePermission(admin, admin, admin);

  }

    
  
}
