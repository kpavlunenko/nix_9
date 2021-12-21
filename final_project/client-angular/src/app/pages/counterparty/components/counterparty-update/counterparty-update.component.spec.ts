import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterpartyUpdateComponent } from './counterparty-update.component';

describe('CounterpartyUpdateComponent', () => {
  let component: CounterpartyUpdateComponent;
  let fixture: ComponentFixture<CounterpartyUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CounterpartyUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CounterpartyUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
