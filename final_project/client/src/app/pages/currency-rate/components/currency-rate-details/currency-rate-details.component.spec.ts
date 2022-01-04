import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyRateDetailsComponent } from './currency-rate-details.component';

describe('CurrencyRateDetailsComponent', () => {
  let component: CurrencyRateDetailsComponent;
  let fixture: ComponentFixture<CurrencyRateDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyRateDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyRateDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
