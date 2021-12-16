import { TestBed } from '@angular/core/testing';

import { BankOperationApiService } from './bank-operation-api.service';

describe('BankOperationApiService', () => {
  let service: BankOperationApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankOperationApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
