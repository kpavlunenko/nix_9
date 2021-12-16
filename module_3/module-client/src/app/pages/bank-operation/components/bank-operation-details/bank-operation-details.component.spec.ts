import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankOperationDetailsComponent } from './bank-operation-details.component';

describe('BankOperationDetailsComponent', () => {
  let component: BankOperationDetailsComponent;
  let fixture: ComponentFixture<BankOperationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankOperationDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankOperationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
