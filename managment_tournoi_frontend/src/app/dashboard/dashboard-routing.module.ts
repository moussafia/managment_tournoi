import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import * as path from 'path';

const routes = [
  {}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports:[]
})
export class DashboardRoutingModule { }
