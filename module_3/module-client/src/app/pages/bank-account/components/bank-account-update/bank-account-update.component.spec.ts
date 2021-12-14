import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankAccountUpdateComponent } from './bank-account-update.component';

describe('BankAccountUpdateComponent', () => {
  let component: BankAccountUpdateComponent;
  let fixture: ComponentFixture<BankAccountUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankAccountUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankAccountUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
