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
import { UpdateUserComponent } from './user/update-user/update-user.component';
import { UpdateProfileComponent } from './user/update-user/update-profile/update-profile.component';
import { UpdatePasswordComponent } from './user/update-user/update-password/update-password.component';
import { AssignRoleComponent } from './user/update-user/assign-role/assign-role.component';
import { UpdateTeamComponent } from './team/update-team/update-team.component';
import { GroupComponent } from './group/group.component';
import { MatchComponent } from './match/match.component';
import { AddMatchComponent } from './match/add-match/add-match.component';
import { AuthGuard } from '../auth/gard/auth.guard';
import { BdeGuard } from '../auth/gard/bde.guard';
import { DelegateGuard } from '../auth/gard/delegate.guard';
import { AdminGuard } from '../auth/gard/admin.guard';


const routes: Routes = [
  {
    path: '', 
  component: DashboardComponent,
  children:[
    { path:'home', component: HomeComponent , canActivate: [AuthGuard, DelegateGuard]},   
    { path:'user', 
    component: UserComponent ,
    children:[
      {path:'users', component: UsersComponent, canActivate:[DelegateGuard]},
      {path:'add-users', component: AddUsersComponent, canActivate:[DelegateGuard]},
      {path:'add-user', component: AddUserComponent, canActivate:[DelegateGuard]},
      {path:'update-member/:id', component: UpdateUserComponent, 
          children:[
            {path:'update-profile/:id', component: UpdateProfileComponent},
            {path:'update-password/:id', component: UpdatePasswordComponent},
            {path:'assign-role/:id', component: AssignRoleComponent},
            {path:'', redirectTo: 'update-profile/default-id', pathMatch: 'full'}
          ], 
          canActivate:[AdminGuard]
      },
      {path:'', redirectTo: 'users' , pathMatch: 'full'},
    ],
    canActivate: [AuthGuard]
    },   
    { path:'team', 
    component: TeamComponent ,
    children:[
      {path:'teams', component: TeamsComponent},
      {path:'add-team', component: AddTeamComponent},
      {path:'update-team/:id', component: UpdateTeamComponent},
      {path:'', redirectTo: 'teams' , pathMatch: 'full'},

    ],
    canActivate: [DelegateGuard]
    },   
    { path:'group', 
    component: GroupComponent,  canActivate: [DelegateGuard]
    },   
    { path:'match', 
    component: MatchComponent,
    children:[
      {path:'add-match', component: AddMatchComponent},
      {path:'', redirectTo: 'add-match' , pathMatch: 'full'},
    ],
    canActivate: [DelegateGuard]
    },   
    { path:'rule', 
    component: RuleComponent ,
    children:[
      {path:'rules', component: RulesComponent},
      {path:'add-rule', component: AddRuleComponent},
      {path:'get-rules', component: GetRulesComponent},
      {path:'', redirectTo: 'rules' , pathMatch: 'full'},
    ],
    canActivate: [AuthGuard, DelegateGuard]
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
