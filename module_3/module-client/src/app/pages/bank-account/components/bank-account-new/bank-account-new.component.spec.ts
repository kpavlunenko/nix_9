import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankAccountNewComponent } from './bank-account-new.component';

describe('BankAccountNewComponent', () => {
  let component: BankAccountNewComponent;
  let fixture: ComponentFixture<BankAccountNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankAccountNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankAccountNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
