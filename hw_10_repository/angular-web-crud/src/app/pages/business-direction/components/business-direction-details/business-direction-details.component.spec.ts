import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessDirectionDetailsComponent } from './business-direction-details.component';

describe('BusinessDirectionDetailsComponent', () => {
  let component: BusinessDirectionDetailsComponent;
  let fixture: ComponentFixture<BusinessDirectionDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessDirectionDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessDirectionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
