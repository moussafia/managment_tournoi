import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MeState } from '../state/me.reducer';
import { Store } from '@ngrx/store';

@Injectable({
  providedIn: 'root'
})
export class BdeGuard implements CanActivate {
  
  constructor(private store: Store<MeState>, private router: Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return true;
  }
  
}
