import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CounterpartyItemsComponent, CounterpartyDetailsComponent, CounterpartyNewComponent, CounterpartyUpdateComponent } from "./components";
import { CounterpartyRoutingModule } from "./counterparty-routing.module";
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    CounterpartyItemsComponent,
    CounterpartyDetailsComponent,
    CounterpartyNewComponent,
    CounterpartyUpdateComponent
  ],
  imports: [
    CommonModule,
    CounterpartyRoutingModule,
    ReactiveFormsModule
  ]
})
export class CounterpartyModule {
}
