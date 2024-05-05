import { Injectable } from '@angular/core';
import { Actions, ofType, createEffect } from '@ngrx/effects';
import { of } from 'rxjs';
import { concatMap, map, catchError } from 'rxjs/operators';
import { MeApiAction, MePageAction } from './action';
import { AuthService } from '../auth.service';

@Injectable()
export class AuthEffects {
    constructor(private action$: Actions, private authService: AuthService) {}

    authUser$ = createEffect(() =>
        this.action$.pipe(
            ofType(MePageAction.meActionPage),
            concatMap((action) =>
                this.authService.me().pipe(
                    map((user) => {
                     console.log("User successfully logged in:", user);
                        
                        return MeApiAction.meUserSuccess({user});
                    }),
                    catchError((error) => of(MeApiAction.meUserFailure({ error })))
                )
            )
        )
    );

}
