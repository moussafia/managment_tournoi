import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MeState } from '../state/me.reducer';
import { Store } from '@ngrx/store';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';
import { getUserSucces } from '../state';
import { MePageAction } from '../state/action';
import { GuardHelper } from './gard.helper';

@Injectable({
  providedIn: 'root'
})
export class BdeGuard implements CanActivate {

  readonly roleEnum = RoleEnum;
  constructor(private guardHelper: GuardHelper){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    return this.guardHelper.checkAccesPagePermission(this.roleEnum.BDE, this.roleEnum.ADMIN, this.roleEnum.BDE);

  }


}

