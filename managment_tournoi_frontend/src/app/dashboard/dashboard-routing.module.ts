import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { UsersComponent } from './user/users/users.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { TeamComponent } from './team/team.component';
import { TeamsComponent } from './team/teams/teams.component';
import { AddTeamComponent } from './team/add-team/add-team.component';
import { RuleComponent } from './rule/rule.component';
import { RulesComponent } from './rule/rules/rules.component';
import { AddRuleComponent } from './rule/add-rule/add-rule.component';
import { GetRulesComponent } from './rule/get-rules/get-rules.component';


const routes: Routes = [
  {
    path: '', 
  component: DashboardComponent,
  children:[
    { path:'home', component: HomeComponent },   
    { path:'user', 
    component: UserComponent ,
    children:[
      {path:'users', component: UsersComponent},
      {path:'add-users', component: AddUsersComponent},
      {path:'add-user', component: AddUserComponent},
      {path:'', redirectTo: 'users' , pathMatch: 'full'},

    ]
    },   
    { path:'team', 
    component: TeamComponent ,
    children:[
      {path:'teams', component: TeamsComponent},
      {path:'add-team', component: AddTeamComponent},
      {path:'', redirectTo: 'teams' , pathMatch: 'full'},

    ]
    },   
    { path:'rule', 
    component: RuleComponent ,
    children:[
      {path:'rules', component: RulesComponent},
      {path:'add-rule', component: AddRuleComponent},
      {path:'get-rules', component: GetRulesComponent},
      {path:'', redirectTo: 'rules' , pathMatch: 'full'},

    ]
    },   
    { path: '', redirectTo: 'home', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
      exports:[RouterModule]
})
export class DashboardRoutingModule { }
