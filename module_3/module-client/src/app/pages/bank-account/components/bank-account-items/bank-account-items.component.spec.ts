import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankAccountItemsComponent } from './bank-account-items.component';

describe('BankAccountItemsComponent', () => {
  let component: BankAccountItemsComponent;
  let fixture: ComponentFixture<BankAccountItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankAccountItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankAccountItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
