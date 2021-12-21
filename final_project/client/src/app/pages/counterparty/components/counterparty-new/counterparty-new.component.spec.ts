import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterpartyNewComponent } from './counterparty-new.component';

describe('CounterpartyNewComponent', () => {
  let component: CounterpartyNewComponent;
  let fixture: ComponentFixture<CounterpartyNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CounterpartyNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CounterpartyNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
