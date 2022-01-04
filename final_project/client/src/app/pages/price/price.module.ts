import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  PriceItemsComponent,
  PriceNewComponent,
  PriceDetailsComponent,
  PriceUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {PriceRoutingModule} from "./price-routing.module";
import {MatRadioModule} from "@angular/material/radio";

@NgModule({
  declarations: [
    PriceItemsComponent,
    PriceNewComponent,
    PriceDetailsComponent,
    PriceUpdateComponent
  ],
  imports: [
    CommonModule,
    PriceRoutingModule,
    ReactiveFormsModule,
    MatRadioModule
  ]
})
export class PriceModule {
}
