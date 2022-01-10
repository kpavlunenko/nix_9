import { TestBed } from '@angular/core/testing';

import { SalesInvoiceApiService } from './sales-invoice-api.service';

describe('SalesInvoiceApiService', () => {
  let service: SalesInvoiceApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalesInvoiceApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
