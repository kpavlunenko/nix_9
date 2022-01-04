import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyRateNewComponent } from './currency-rate-new.component';

describe('CurrencyRateNewComponent', () => {
  let component: CurrencyRateNewComponent;
  let fixture: ComponentFixture<CurrencyRateNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyRateNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyRateNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
