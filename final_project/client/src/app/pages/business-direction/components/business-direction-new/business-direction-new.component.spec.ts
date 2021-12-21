import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessDirectionNewComponent } from './business-direction-new.component';

describe('BusinessDirectionNewComponent', () => {
  let component: BusinessDirectionNewComponent;
  let fixture: ComponentFixture<BusinessDirectionNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessDirectionNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessDirectionNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
