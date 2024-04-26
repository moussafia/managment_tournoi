import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
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

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute){}
  ngOnInit(): void {
   this.loadMembers()
  }

 
  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    console.log('page index '+ this.pageIndex +' page size ' + this.pageSize)
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
  updateUser(idMember : string) {
    this.router.navigateByUrl(`/user/update-member/${idMember}/update-profile/${idMember}`);
   }
   searchMember(event: any){
    const searchText = event.target.value;
    console.log(searchText)
    this.userService.searchMember(searchText).subscribe({
      next: data => this.memberShowDto = data
    })
   }
}

