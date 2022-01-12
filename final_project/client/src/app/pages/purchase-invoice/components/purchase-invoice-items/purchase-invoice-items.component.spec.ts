import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseInvoiceItemsComponent } from './purchase-invoice-items.component';

describe('PurchaseInvoiceItemsComponent', () => {
  let component: PurchaseInvoiceItemsComponent;
  let fixture: ComponentFixture<PurchaseInvoiceItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseInvoiceItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseInvoiceItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
