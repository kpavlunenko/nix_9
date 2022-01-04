import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceNewComponent } from './price-new.component';

describe('PriceNewComponent', () => {
  let component: PriceNewComponent;
  let fixture: ComponentFixture<PriceNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
