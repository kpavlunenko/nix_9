import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementItemsComponent } from './agreement-items.component';

describe('AgreementItemsComponent', () => {
  let component: AgreementItemsComponent;
  let fixture: ComponentFixture<AgreementItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgreementItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
