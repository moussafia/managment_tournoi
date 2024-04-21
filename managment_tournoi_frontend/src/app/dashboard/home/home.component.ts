import { Component, OnInit } from '@angular/core';
import { data } from 'autoprefixer';
import { MatchShowDto } from 'src/app/dto/matchDto/matchShowDto';
import { MatchService } from 'src/app/services/match/match.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  viewDate: Date = new Date();
  matchToday?: MatchShowDto
  errorMessage?: string

  constructor(private matchService:MatchService){
    this.viewDate.setDate(1);
  }
  ngOnInit(): void {
    this.matchService.getMatchToday().subscribe({
      next: data => {this.matchToday =  data
        console.log(data)
      },
      error: err => this.errorMessage = err.error.message
    })
  }

}
