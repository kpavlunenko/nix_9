import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceTypeItemsComponent } from './price-type-items.component';

describe('PriceTypeItemsComponent', () => {
  let component: PriceTypeItemsComponent;
  let fixture: ComponentFixture<PriceTypeItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceTypeItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceTypeItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
