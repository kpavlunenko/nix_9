import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceTypeDetailsComponent } from './price-type-details.component';

describe('PriceTypeDetailsComponent', () => {
  let component: PriceTypeDetailsComponent;
  let fixture: ComponentFixture<PriceTypeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceTypeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceTypeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
