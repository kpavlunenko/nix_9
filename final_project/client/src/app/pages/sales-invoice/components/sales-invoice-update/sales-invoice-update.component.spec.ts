import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesInvoiceUpdateComponent } from './sales-invoice-update.component';

describe('SalesInvoiceUpdateComponent', () => {
  let component: SalesInvoiceUpdateComponent;
  let fixture: ComponentFixture<SalesInvoiceUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalesInvoiceUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesInvoiceUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
