import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseInvoiceNewComponent } from './purchase-invoice-new.component';

describe('PurchaseInvoiceNewComponent', () => {
  let component: PurchaseInvoiceNewComponent;
  let fixture: ComponentFixture<PurchaseInvoiceNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseInvoiceNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseInvoiceNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
