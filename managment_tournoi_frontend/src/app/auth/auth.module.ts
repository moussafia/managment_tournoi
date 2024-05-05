import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogINComponent } from './log-in/log-in.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    LogINComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', component: LogINComponent}
    ])
  ]
})
export class AuthModule { }
