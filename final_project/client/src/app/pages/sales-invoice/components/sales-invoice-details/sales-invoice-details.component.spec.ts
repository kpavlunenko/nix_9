import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesInvoiceDetailsComponent } from './sales-invoice-details.component';

describe('SalesInvoiceDetailsComponent', () => {
  let component: SalesInvoiceDetailsComponent;
  let fixture: ComponentFixture<SalesInvoiceDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalesInvoiceDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesInvoiceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
