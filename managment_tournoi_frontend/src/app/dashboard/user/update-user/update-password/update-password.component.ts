import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {
  idMember?: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
     this.route.paramMap.subscribe(params => {
          this.idMember = params.get('id');
    })
    console.log('hi '+ this.idMember)

  }
}
