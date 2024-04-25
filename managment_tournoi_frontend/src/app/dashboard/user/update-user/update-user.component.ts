import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  idMember?: any;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
     this.route.paramMap.subscribe(params => {
        this.idMember = params.get('id');
    })

  }
}
