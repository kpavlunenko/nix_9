import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  PriceTypeItemsComponent,
  PriceTypeNewComponent,
  PriceTypeDetailsComponent,
  PriceTypeUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {PriceTypeRoutingModule} from "./price-type-routing.module";
import {MatRadioModule} from "@angular/material/radio";

@NgModule({
  declarations: [
    PriceTypeItemsComponent,
    PriceTypeNewComponent,
    PriceTypeDetailsComponent,
    PriceTypeUpdateComponent
  ],
  imports: [
    CommonModule,
    PriceTypeRoutingModule,
    ReactiveFormsModule,
    MatRadioModule
  ]
})
export class PriceTypeModule {
}
