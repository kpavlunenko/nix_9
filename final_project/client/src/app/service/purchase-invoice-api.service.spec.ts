import { TestBed } from '@angular/core/testing';

import { PurchaseInvoiceApiService } from './purchase-invoice-api.service';

describe('PurchaseInvoiceApiService', () => {
  let service: PurchaseInvoiceApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PurchaseInvoiceApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
