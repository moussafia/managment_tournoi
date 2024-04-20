import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

viewDate: Date = new Date();
constructor(){
  this.viewDate.setDate(1);
}

dayClicked(day: any): void {
  console.log('Clicked on: ', day);
}
}
