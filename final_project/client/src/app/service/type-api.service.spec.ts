import { TestBed } from '@angular/core/testing';

import { TypeApiService } from './type-api.service';

describe('TypeApiService', () => {
  let service: TypeApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
