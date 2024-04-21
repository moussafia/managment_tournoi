import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetRulesComponent } from './get-rules.component';

describe('GetRulesComponent', () => {
  let component: GetRulesComponent;
  let fixture: ComponentFixture<GetRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetRulesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
