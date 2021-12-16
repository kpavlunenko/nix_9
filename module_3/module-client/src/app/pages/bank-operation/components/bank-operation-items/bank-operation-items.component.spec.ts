import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankOperationItemsComponent } from './bank-operation-items.component';

describe('BankOperationItemsComponent', () => {
  let component: BankOperationItemsComponent;
  let fixture: ComponentFixture<BankOperationItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankOperationItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankOperationItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
