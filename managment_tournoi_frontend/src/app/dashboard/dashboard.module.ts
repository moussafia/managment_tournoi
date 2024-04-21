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
import { TeamComponent } from './team/team.component';
import { AddTeamComponent } from './team/add-team/add-team.component';
import { TeamsComponent } from './team/teams/teams.component';
import { RuleComponent } from './rule/rule.component';
import { AddRuleComponent } from './rule/add-rule/add-rule.component';
import { GetRulesComponent } from './rule/get-rules/get-rules.component';
import { RulesComponent } from './rule/rules/rules.component';
import { FormsModule } from '@angular/forms';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
  declarations: [
    DashboardComponent,
    HomeComponent,
    AddUsersComponent,
    AddUserComponent,
    UserComponent,
    TeamComponent,
    AddTeamComponent,
    TeamsComponent,
    RuleComponent,
    AddRuleComponent,
    GetRulesComponent,
    RulesComponent,
    AddRuleComponent
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
    FormsModule,
    BrowserModule,
    CKEditorModule

  ],
  providers:[
]
})
export class DashboardModule {}
