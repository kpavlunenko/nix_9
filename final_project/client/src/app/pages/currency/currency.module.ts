import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  CurrencyItemsComponent,
  CurrencyDetailsComponent,
  CurrencyNewComponent,
  CurrencyUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {CurrencyRoutingModule} from "./currency-routing.module";

@NgModule({
  declarations: [
    CurrencyItemsComponent,
    CurrencyDetailsComponent,
    CurrencyNewComponent,
    CurrencyUpdateComponent
  ],
  imports: [
    CommonModule,
    CurrencyRoutingModule,
    ReactiveFormsModule
  ]
})
export class CurrencyModule {
}
