import { Injectable } from '@angular/core';
import { Actions, ofType, createEffect } from '@ngrx/effects';
import { of } from 'rxjs';
import { concatMap, map, catchError } from 'rxjs/operators';
import { MeApiAction, MePageAction } from './action';
import { AuthService } from '../auth.service';

@Injectable()
export class MeEffects {
    constructor(private action$: Actions, private authService: AuthService) {}

    authUser$ = createEffect(() =>
        this.action$.pipe(
            ofType(MePageAction.me),
            concatMap((action) =>
                this.authService.me().pipe(
                    map((user) => MeApiAction.meUserSuccess({user})),
                    catchError((error) => of(MeApiAction.meUserFailure({ error })))
                )
            )
        )
    );

}
