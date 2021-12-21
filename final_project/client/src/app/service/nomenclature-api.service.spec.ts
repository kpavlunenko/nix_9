import { TestBed } from '@angular/core/testing';

import { NomenclatureApiService } from './nomenclature-api.service';

describe('NomenclatureApiService', () => {
  let service: NomenclatureApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NomenclatureApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
