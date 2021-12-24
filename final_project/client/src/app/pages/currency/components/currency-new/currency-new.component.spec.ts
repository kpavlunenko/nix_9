import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyNewComponent } from './currency-new.component';

describe('CurrencyNewComponent', () => {
  let component: CurrencyNewComponent;
  let fixture: ComponentFixture<CurrencyNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
