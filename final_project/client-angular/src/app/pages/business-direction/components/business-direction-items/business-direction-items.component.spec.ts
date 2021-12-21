import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessDirectionItemsComponent } from './business-direction-items.component';

describe('BusinessDirectionItemsComponent', () => {
  let component: BusinessDirectionItemsComponent;
  let fixture: ComponentFixture<BusinessDirectionItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessDirectionItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessDirectionItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
