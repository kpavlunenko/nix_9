import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NomenclatureUpdateComponent } from './nomenclature-update.component';

describe('NomenclatureUpdateComponent', () => {
  let component: NomenclatureUpdateComponent;
  let fixture: ComponentFixture<NomenclatureUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NomenclatureUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NomenclatureUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
