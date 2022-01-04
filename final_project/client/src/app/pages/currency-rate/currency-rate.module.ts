import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  CurrencyRateItemsComponent,
  CurrencyRateDetailsComponent,
  CurrencyRateNewComponent,
  CurrencyRateUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {CurrencyRateRoutingModule} from "./currency-rate-routing.module";

@NgModule({
  declarations: [
    CurrencyRateItemsComponent,
    CurrencyRateDetailsComponent,
    CurrencyRateNewComponent,
    CurrencyRateUpdateComponent
  ],
  imports: [
    CommonModule,
    CurrencyRateRoutingModule,
    ReactiveFormsModule
  ]
})
export class CurrencyRateModule {
}
