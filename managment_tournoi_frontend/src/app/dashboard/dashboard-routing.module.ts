import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { UsersComponent } from './user/users/users.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import { AddUserComponent } from './user/add-user/add-user.component';


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
    { path: '', redirectTo: 'home', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
      exports:[RouterModule]
})
export class DashboardRoutingModule { }
