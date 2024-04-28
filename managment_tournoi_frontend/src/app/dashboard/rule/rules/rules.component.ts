import { Component, OnInit } from '@angular/core';
import { Rule } from 'src/app/dto/ruleDto/rule';
import { RuleService } from 'src/app/services/rule/rule.service';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})
export class RulesComponent implements OnInit {

  rule?: Rule[];
  constructor(private ruleService: RuleService){}
  
  ngOnInit(): void {
    this.ruleService.getAllRule().subscribe({
      next: data => this.rule = data
    })
  }
}
