import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRouterModule } from './user-router.module';
import { HomeComponent } from './home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';



@NgModule({
  declarations: [
    HomeComponent, 
    NavBarComponent
  ],
  imports: [
    CommonModule,
    UserRouterModule,
  ]
})
export class UserModule { }
