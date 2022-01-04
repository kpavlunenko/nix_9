import { TestBed } from '@angular/core/testing';

import { CurrencyRateApiService } from './currency-rate-api.service';

describe('CurrencyRateApiService', () => {
  let service: CurrencyRateApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CurrencyRateApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
