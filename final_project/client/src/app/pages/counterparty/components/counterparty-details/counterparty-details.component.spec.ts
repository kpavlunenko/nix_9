import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterpartyDetailsComponent } from './counterparty-details.component';

describe('CounterpartyDetailsComponent', () => {
  let component: CounterpartyDetailsComponent;
  let fixture: ComponentFixture<CounterpartyDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CounterpartyDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CounterpartyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
