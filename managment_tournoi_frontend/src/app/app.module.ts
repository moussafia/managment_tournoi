import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { AuthModule } from './auth/auth.module';
import { MeEffects } from './auth/state/me.effects';
import { MeReducer } from './auth/state/me.reducer';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AccessTokenInterceptor } from './interceptor/access-token.interceptor';
import { RefreshTokenInterceptor } from './interceptor/refresh-token.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DashboardModule,
    SweetAlert2Module.forRoot(),
    StoreModule.forRoot({'user': MeReducer}),
    EffectsModule.forRoot([MeEffects]),
    AuthModule,
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() })  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AccessTokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: RefreshTokenInterceptor, multi: true}

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
