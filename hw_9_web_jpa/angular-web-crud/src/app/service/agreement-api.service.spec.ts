import { TestBed } from '@angular/core/testing';

import { AgreementApiService } from './agreement-api.service';

describe('AgreementApiService', () => {
  let service: AgreementApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AgreementApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
