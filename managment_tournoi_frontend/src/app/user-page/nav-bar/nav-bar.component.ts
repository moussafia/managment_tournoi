import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AuthService } from 'src/app/auth/auth.service';
import { MeState } from 'src/app/auth/state/me.reducer';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit{

  isOpen: boolean = true;
  //memberShowDto?: MemberShowDto;

  constructor(private store: Store<MeState>, private authService: AuthService, private router: Router){}

  ngOnInit(): void {
   // this.store.select(getUserSucces).subscribe({
   //   next: data => this.memberShowDto = data
   // })
  }


  toggleOpen() {
  this.isOpen = !this.isOpen;
  }

  logOut(){
    this.authService.lougout();
    this.router.navigate(['/auth/login']);

  }


}