import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementNewComponent } from './agreement-new.component';

describe('AgreementNewComponent', () => {
  let component: AgreementNewComponent;
  let fixture: ComponentFixture<AgreementNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgreementNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
