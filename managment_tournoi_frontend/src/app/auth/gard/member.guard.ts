import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MeState } from '../state/me.reducer';
import { getUserFailure, getUserSucces } from '../state';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';
import { MePageAction } from '../state/action';

@Injectable({
  providedIn: 'root'
})
export class MemberGuard implements CanActivate {

  memberShowDto?: MemberShowDto;
  readonly roleEnum = RoleEnum;
  canNavigate:boolean = false;

  constructor(private store: Store<MeState>, private router: Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    this.store.select(getUserSucces).subscribe({
      next: (data: MemberShowDto) => {
        
        this.memberShowDto = data;
        if(this.memberShowDto.role ){
            this.checkRoleUser(data);

        }else {

          this.store.dispatch(MePageAction.me());
          this.store.select(getUserSucces).subscribe({
              next: (data: MemberShowDto) => {
                this.checkRoleUser(data);
              }
          })

        }         
      
      }
    })


    return this.canNavigate;
  }


  checkRoleUser(data : MemberShowDto){
    this.memberShowDto = data;
    this.canNavigate = this.memberShowDto.role.name == this.roleEnum.MEMBER;
  }
  
}
