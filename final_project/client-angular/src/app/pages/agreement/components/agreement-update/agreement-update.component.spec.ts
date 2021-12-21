import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementUpdateComponent } from './agreement-update.component';

describe('AgreementUpdateComponent', () => {
  let component: AgreementUpdateComponent;
  let fixture: ComponentFixture<AgreementUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgreementUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
