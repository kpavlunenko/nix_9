import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterpartyItemsComponent } from './counterparty-items.component';

describe('CounterpartyItemsComponent', () => {
  let component: CounterpartyItemsComponent;
  let fixture: ComponentFixture<CounterpartyItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CounterpartyItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CounterpartyItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
