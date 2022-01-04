import { TestBed } from '@angular/core/testing';

import { PriceTypeApiService } from './price-type-api.service';

describe('PriceTypeApiService', () => {
  let service: PriceTypeApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriceTypeApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
