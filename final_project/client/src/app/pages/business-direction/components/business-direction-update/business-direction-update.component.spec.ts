import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessDirectionUpdateComponent } from './business-direction-update.component';

describe('BusinessDirectionUpdateComponent', () => {
  let component: BusinessDirectionUpdateComponent;
  let fixture: ComponentFixture<BusinessDirectionUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessDirectionUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessDirectionUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
