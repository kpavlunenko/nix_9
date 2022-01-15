import { TestBed } from '@angular/core/testing';

import { SalesIncomeApiService } from './sales-income-api.service';

describe('SalesIncomeApiService', () => {
  let service: SalesIncomeApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalesIncomeApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
