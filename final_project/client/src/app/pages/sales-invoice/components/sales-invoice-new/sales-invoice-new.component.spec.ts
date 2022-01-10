import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesInvoiceNewComponent } from './sales-invoice-new.component';

describe('SalesInvoiceNewComponent', () => {
  let component: SalesInvoiceNewComponent;
  let fixture: ComponentFixture<SalesInvoiceNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalesInvoiceNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesInvoiceNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
