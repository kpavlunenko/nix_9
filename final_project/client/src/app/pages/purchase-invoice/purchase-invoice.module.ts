import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  PurchaseInvoiceItemsComponent,
  PurchaseInvoiceDetailsComponent,
  PurchaseInvoiceNewComponent,
  PurchaseInvoiceUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {PurchaseInvoiceRoutingModule} from "./purchase-invoice-routing.module";
import {MatRadioModule} from "@angular/material/radio";
import {NgxPermissionsModule} from "ngx-permissions";

@NgModule({
  declarations: [
    PurchaseInvoiceItemsComponent,
    PurchaseInvoiceDetailsComponent,
    PurchaseInvoiceNewComponent,
    PurchaseInvoiceUpdateComponent
  ],
  imports: [
    CommonModule,
    PurchaseInvoiceRoutingModule,
    ReactiveFormsModule,
    MatRadioModule,
    NgxPermissionsModule
  ]
})
export class PurchaseInvoiceModule {
}
