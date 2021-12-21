import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NomenclatureItemsComponent } from './nomenclature-items.component';

describe('NomenclatureItemsComponent', () => {
  let component: NomenclatureItemsComponent;
  let fixture: ComponentFixture<NomenclatureItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NomenclatureItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NomenclatureItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
