import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NomenclatureNewComponent } from './nomenclature-new.component';

describe('NomenclatureNewComponent', () => {
  let component: NomenclatureNewComponent;
  let fixture: ComponentFixture<NomenclatureNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NomenclatureNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NomenclatureNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
