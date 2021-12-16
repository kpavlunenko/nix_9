import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankOperationNewComponent } from './bank-operation-new.component';

describe('BankOperationNewComponent', () => {
  let component: BankOperationNewComponent;
  let fixture: ComponentFixture<BankOperationNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankOperationNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankOperationNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
