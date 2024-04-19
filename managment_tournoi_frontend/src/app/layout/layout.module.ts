import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidbarComponent } from './sidbar/sidbar.component';
import { NavbarComponent } from './navbar/navbar.component';



@NgModule({
  declarations: [
    SidbarComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    SidbarComponent,
    NavbarComponent
  ]
})
export class LayoutModule { }
