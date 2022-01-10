import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesInvoiceItemsComponent } from './sales-invoice-items.component';

describe('SalesInvoiceItemsComponent', () => {
  let component: SalesInvoiceItemsComponent;
  let fixture: ComponentFixture<SalesInvoiceItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalesInvoiceItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesInvoiceItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
