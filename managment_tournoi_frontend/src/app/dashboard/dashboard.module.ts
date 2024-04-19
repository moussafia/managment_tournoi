import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard.component';
import { LayoutModule } from '../layout/layout.module';



@NgModule({
  declarations: [
    HomeComponent,
    DashboardComponent,

  ],
  imports: [
    CommonModule,
    LayoutModule
  ]
})
export class DashboardModule {
  
 }
