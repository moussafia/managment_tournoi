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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { UsersComponent } from './user/users/users.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { UpdateUserComponent } from './user/update-user/update-user.component';
import { UpdateProfileComponent } from './user/update-user/update-profile/update-profile.component';
import { UpdatePasswordComponent } from './user/update-user/update-password/update-password.component';
import { AssignRoleComponent } from './user/update-user/assign-role/assign-role.component';
import { ClickOutsideDirectiveDirective } from '../directive/click-outside-directive.directive';
import { UpdateTeamComponent } from './team/update-team/update-team.component';
import { GroupComponent } from './group/group.component';
import { MatchComponent } from './match/match.component';
import { AddMatchComponent } from './match/add-match/add-match.component';

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
    AddRuleComponent,
    UsersComponent,
    UpdateUserComponent,
    UpdateProfileComponent,
    UpdatePasswordComponent,
    AssignRoleComponent,
    ClickOutsideDirectiveDirective,
    UpdateTeamComponent,
    GroupComponent,
    MatchComponent,
    AddMatchComponent
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
    CKEditorModule,
    HttpClientModule,
    MatPaginatorModule,
    SweetAlert2Module,
    ReactiveFormsModule,
  ],
  providers:[
]
})
export class DashboardModule {}
