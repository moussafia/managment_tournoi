import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-assign-role',
  templateUrl: './assign-role.component.html',
  styleUrls: ['./assign-role.component.css']
})
export class AssignRoleComponent implements OnInit {
  idMember?: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
     this.route.paramMap.subscribe(params => {
          this.idMember = params.get('id');
    })
    console.log('hi '+ this.idMember)
  }
}
