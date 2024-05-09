import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenDecoced } from 'src/app/auth/dto/TokenDecoded';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { RoleEnum } from 'src/app/dto/enum/roleEnum';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {
  memberShowDto?:MemberShowDto[];
  pageSize: number = 5;
  pageIndex: number = 0;
  totalItems: number = 0;
  jwtDecode:TokenDecoced | null = null;
  readonly roleEnume = RoleEnum

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute, private authService: AuthService){}
  ngOnInit(): void {
    this.jwtDecode = this.authService.tokenDecoded()
   this.loadMembers()
  }

 
  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadMembers();
  }
  loadMembers(): void { 
    this.userService.getAllMembers(this.pageIndex, this.pageSize)
      .subscribe({
        next: data => {
          this.memberShowDto = data.content
          this.totalItems = data.totalElements
        }
      })
  }

   searchMember(event: any){
    const searchText = event.target.value;
    this.userService.searchMember(searchText, this.pageIndex, this.pageSize).subscribe({
      next: data => {
        this.memberShowDto = data.content
        this.totalItems = data.totalElements
      }
    })
   }

   updateUser(idMember : string) {
    this.router.navigateByUrl(`/user/update-member/${idMember}/update-profile/${idMember}`);
   }
}

