import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SidbarComponent } from './layout/sidbar/sidbar.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {path: '**', component: DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
