import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseInvoiceUpdateComponent } from './purchase-invoice-update.component';

describe('PurchaseInvoiceUpdateComponent', () => {
  let component: PurchaseInvoiceUpdateComponent;
  let fixture: ComponentFixture<PurchaseInvoiceUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseInvoiceUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseInvoiceUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
