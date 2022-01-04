import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceTypeNewComponent } from './price-type-new.component';

describe('PriceTypeNewComponent', () => {
  let component: PriceTypeNewComponent;
  let fixture: ComponentFixture<PriceTypeNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceTypeNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceTypeNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
