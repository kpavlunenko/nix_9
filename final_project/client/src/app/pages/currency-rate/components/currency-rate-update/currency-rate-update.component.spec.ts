import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyRateUpdateComponent } from './currency-rate-update.component';

describe('CurrencyRateUpdateComponent', () => {
  let component: CurrencyRateUpdateComponent;
  let fixture: ComponentFixture<CurrencyRateUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyRateUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyRateUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
