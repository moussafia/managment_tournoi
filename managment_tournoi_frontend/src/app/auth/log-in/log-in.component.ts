import { Component, ViewChild } from '@angular/core';
import { AuthService } from '../auth.service';
import { AuthenticationRequestDto } from '../dto/AuthenticationRequestDto';
import { DataResponse, DataState } from 'src/app/dto/data.state.';
import { catchError, map, of, startWith } from 'rxjs';
import { AuthenticationResponseDto } from '../dto/AuthenticationResponseDto';
import { NgForm } from '@angular/forms';
import { Store } from '@ngrx/store';
import { MeState } from '../state/me.reducer';
import { Router } from '@angular/router';
import { getUserFailure, getUserSucces } from '../state';
import { MePageAction } from '../state/action';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogINComponent {
  EU?: string;
  password?: string;
  userAuthRequest?: AuthenticationRequestDto;
  readonly dataState = DataState;
  userResponseDto?: DataResponse<AuthenticationResponseDto, any>;
  textError?: string;
  memberShowDto?: MemberShowDto;

  readonly roleEnum = RoleEnum;


  @ViewChild('formAuth') formAuth?: NgForm;

  constructor(private authService: AuthService, private store: Store<MeState>, private router:Router){}



  logIn() {
    if(!this.EU && !this.password)
        return;

    this.userAuthRequest= {username: this.EU, password: this.password} as AuthenticationRequestDto;
    
    this.authService.signIn(this.userAuthRequest).pipe(
      map(data=>{
        return ({dataState: this.dataState.LOADED, data: data || []}); 
      }),
      startWith({dataState: this.dataState.LOADING}),
      catchError(error => of({dataState: this.dataState.ERROR, error: error.error.message }))
    ).subscribe({
      next: data => {
        this.userResponseDto = data as DataResponse<AuthenticationResponseDto, any>
        if(data.dataState === this.dataState.LOADED){

          if(this.userResponseDto.data){

              this.authService.jwt = this.userResponseDto.data.access_token;
              this.authService.refreshToken = this.userResponseDto.data.refresh_token;
              this.navigateToRoute();
          }
          
          this.formAuth?.resetForm();
          this.store.dispatch(MePageAction.me());


        }else if(data.dataState === this.dataState.ERROR){
         this.textError = this.userResponseDto.error;

        }

      }
    })
  }

  navigateToRoute(){
    this.store.select(getUserSucces).subscribe({
      next: data => {
        this.memberShowDto = data;

        console.log("LOGiN DATA" + this.memberShowDto.role.name)
        if(this.memberShowDto.role.name != this.roleEnum.MEMBER){
           this.router.navigate(['/home']);
        }else{
          console.log("page member")
        }
      }
    })
  }

}
