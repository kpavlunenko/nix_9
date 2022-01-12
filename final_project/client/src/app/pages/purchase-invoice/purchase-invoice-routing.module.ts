import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { PurchaseInvoiceItemsComponent, PurchaseInvoiceDetailsComponent, PurchaseInvoiceNewComponent, PurchaseInvoiceUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: PurchaseInvoiceItemsComponent
  },
  {
    path: 'new',
    component: PurchaseInvoiceNewComponent
  },
  {
    path: 'details/:id',
    component: PurchaseInvoiceDetailsComponent
  },
  {
    path: 'update/:id',
    component: PurchaseInvoiceUpdateComponent
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class PurchaseInvoiceRoutingModule { }
