import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyRateItemsComponent } from './currency-rate-items.component';

describe('CurrencyRateItemsComponent', () => {
  let component: CurrencyRateItemsComponent;
  let fixture: ComponentFixture<CurrencyRateItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyRateItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyRateItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
