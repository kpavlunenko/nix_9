import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyItemsComponent } from './currency-items.component';

describe('CurrencyItemsComponent', () => {
  let component: CurrencyItemsComponent;
  let fixture: ComponentFixture<CurrencyItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
