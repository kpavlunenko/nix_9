import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NomenclatureDetailsComponent } from './nomenclature-details.component';

describe('NomenclatureDetailsComponent', () => {
  let component: NomenclatureDetailsComponent;
  let fixture: ComponentFixture<NomenclatureDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NomenclatureDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NomenclatureDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
