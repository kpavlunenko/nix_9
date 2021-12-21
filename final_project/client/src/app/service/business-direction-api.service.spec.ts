import { TestBed } from '@angular/core/testing';

import { BusinessDirectionApiService } from './business-direction-api.service';

describe('BusinessDirectionApiService', () => {
  let service: BusinessDirectionApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessDirectionApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
