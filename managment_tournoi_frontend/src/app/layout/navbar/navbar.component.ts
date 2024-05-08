import { Component, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AuthService } from 'src/app/auth/auth.service';
import { getUserSucces } from 'src/app/auth/state';
import { MeState } from 'src/app/auth/state/me.reducer';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  isOpen: boolean = true;
  memberShowDto?: MemberShowDto;

  constructor(private store: Store<MeState>, private authService: AuthService, private router: Router){}

  ngOnInit(): void {
    this.store.select(getUserSucces).subscribe({
      next: data => this.memberShowDto = data
    })
  }


  toggleOpen() {
  this.isOpen = !this.isOpen;
  }

  logOut(){
    this.authService.lougout();
    this.router.navigate(['/auth/login']);

  }


}
