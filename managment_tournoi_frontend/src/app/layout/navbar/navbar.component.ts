import { Component, Input, Output } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
isOpen: boolean = true;
toggleOpen() {
this.isOpen = !this.isOpen;
}

}
