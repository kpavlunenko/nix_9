import { TestBed } from '@angular/core/testing';

import { CounterpartyApiService } from './counterparty-api.service';

describe('CounterpartyApiService', () => {
  let service: CounterpartyApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CounterpartyApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
