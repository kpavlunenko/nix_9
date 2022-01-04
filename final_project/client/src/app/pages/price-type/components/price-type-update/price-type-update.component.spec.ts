import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceTypeUpdateComponent } from './price-type-update.component';

describe('PriceTypeUpdateComponent', () => {
  let component: PriceTypeUpdateComponent;
  let fixture: ComponentFixture<PriceTypeUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceTypeUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceTypeUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
