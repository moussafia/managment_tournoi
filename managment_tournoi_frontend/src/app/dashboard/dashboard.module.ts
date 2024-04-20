import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { LayoutModule } from '../layout/layout.module';
import { RouterOutlet } from '@angular/router';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { HomeComponent } from './home/home.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { UserComponent } from './user/user.component';


@NgModule({
  declarations: [
    DashboardComponent,
    HomeComponent,
    AddUsersComponent,
    AddUserComponent,
    UserComponent
  ],
  imports: [
    CommonModule,
    LayoutModule,
    RouterOutlet,
    DashboardRoutingModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ]
})
export class DashboardModule {}
