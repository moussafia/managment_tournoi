import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GroupComponent } from './group/group.component';
import { HomeComponent } from './home.component';


// eslint-disable-next-line @typescript-eslint/no-unused-vars
const routes: Routes = [
  {path: 'mgroup', component: HomeComponent,
    children: [
      {path: '', component: GroupComponent}
    ]
  },
  {path: '', redirectTo: 'mgroup', pathMatch: 'full'}


]

@NgModule({
  imports: [RouterModule.forChild(routes)],
      exports:[RouterModule]
})
export class UserRouterModule { }
