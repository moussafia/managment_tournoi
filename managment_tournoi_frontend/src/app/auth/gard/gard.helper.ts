import { Router } from "@angular/router";
import { MeState } from "../state/me.reducer";
import { Store } from "@ngrx/store";
import { Observable } from "rxjs";
import { MemberShowDto } from "src/app/dto/appUserFileDto/getDto/memberShowDto";
import { getUserSucces } from "../state";
import { MePageAction } from "../state/action";
import { Injectable } from "@angular/core";


@Injectable({
    providedIn: 'root'
})
export class GuardHelper{
    
    private canNavigate:boolean = false;

    constructor(private store: Store<MeState>, private router: Router){}

    public checkAccesPagePermission(roleP1: string, roleP2: string, roleP3: string): Observable<boolean>{

        return new Observable<boolean>((observer) => {

            this.store.select(getUserSucces).subscribe({
              next: (data: MemberShowDto) => {
                
                if(data.role){
                    this.checkRoleUser(data.role.name, roleP1, roleP2, roleP3); 
                    observer.next(this.canNavigate);
  
                }else {
        
                  this.store.dispatch(MePageAction.me());
                  this.store.select(getUserSucces).subscribe({
                      next: (data: MemberShowDto) => {
                        if(data.role){
                            this.checkRoleUser(data.role.name, roleP1, roleP2, roleP3); 
                            observer.next(this.canNavigate);
                        }
                        
                      }
                  });
        
                }                 
              }
            });
        })
    }


    private checkRoleUser(roleName : string, roleP1: string, roleP2: string, roleP3: string): void{
      this.canNavigate = roleName == roleP1 || roleName == roleP2 || roleName == roleP3;
    }

}