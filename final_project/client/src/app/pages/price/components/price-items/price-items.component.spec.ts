import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceItemsComponent } from './price-items.component';

describe('PriceItemsComponent', () => {
  let component: PriceItemsComponent;
  let fixture: ComponentFixture<PriceItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
