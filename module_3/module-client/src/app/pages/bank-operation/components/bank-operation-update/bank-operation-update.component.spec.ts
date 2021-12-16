import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankOperationUpdateComponent } from './bank-operation-update.component';

describe('BankOperationUpdateComponent', () => {
  let component: BankOperationUpdateComponent;
  let fixture: ComponentFixture<BankOperationUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankOperationUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankOperationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
