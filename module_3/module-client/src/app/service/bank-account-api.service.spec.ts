import { TestBed } from '@angular/core/testing';

import { BankAccountApiService } from './bank-account-api.service';

describe('BankAccountApiService', () => {
  let service: BankAccountApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankAccountApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
