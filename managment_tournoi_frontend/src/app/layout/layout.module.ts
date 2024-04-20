import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidbarComponent } from './sidbar/sidbar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    SidbarComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports:[
    SidbarComponent,
    NavbarComponent
  ]
})
export class LayoutModule { }
