import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/gard/auth.guard';

const routes: Routes = [
  {path: 'dashboard', 
  loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule), canActivate:[AuthGuard]
  },
  {path: 'auth', 
  loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
},
  {path: 'member-page', 
  loadChildren: () => import('./user-page/user.module').then(m => m.UserModule), canActivate:[AuthGuard]
  },
  {path: '', redirectTo:'auth', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
      exports:[RouterModule]
})
export class AppRoutingModule { }
