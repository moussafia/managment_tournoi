import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogINComponent } from './log-in/log-in.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    LogINComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    RouterModule.forChild([
      {path: '', component: LogINComponent}
    ])
  ]
})
export class AuthModule { }
