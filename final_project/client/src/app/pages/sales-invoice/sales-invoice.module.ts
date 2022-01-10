import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  SalesInvoiceItemsComponent,
  SalesInvoiceDetailsComponent,
  SalesInvoiceNewComponent,
  SalesInvoiceUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {SalesInvoiceRoutingModule} from "./sales-invoice-routing.module";
import {MatRadioModule} from "@angular/material/radio";
import {NgxPermissionsModule} from "ngx-permissions";

@NgModule({
  declarations: [
    SalesInvoiceItemsComponent,
    SalesInvoiceDetailsComponent,
    SalesInvoiceNewComponent,
    SalesInvoiceUpdateComponent
  ],
  imports: [
    CommonModule,
    SalesInvoiceRoutingModule,
    ReactiveFormsModule,
    MatRadioModule,
    NgxPermissionsModule
  ]
})
export class SalesInvoiceModule {
}
