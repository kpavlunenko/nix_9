import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CounterpartyItemsComponent, CounterpartyDetailsComponent, CounterpartyNewComponent } from "./components";
import { CounterpartyRoutingModule } from "./counterparty-routing.module";
import { ReactiveFormsModule } from "@angular/forms";


@NgModule({
  declarations: [
    CounterpartyItemsComponent,
    CounterpartyDetailsComponent,
    CounterpartyNewComponent
  ],
  imports: [
    CommonModule,
    CounterpartyRoutingModule,
    ReactiveFormsModule
  ]
})
export class CounterpartyModule {
}
