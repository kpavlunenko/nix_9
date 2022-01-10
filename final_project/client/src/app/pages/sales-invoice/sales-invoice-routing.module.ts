import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { SalesInvoiceItemsComponent, SalesInvoiceDetailsComponent, SalesInvoiceNewComponent, SalesInvoiceUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: SalesInvoiceItemsComponent
  },
  {
    path: 'new',
    component: SalesInvoiceNewComponent
  },
  {
    path: 'details/:id',
    component: SalesInvoiceDetailsComponent
  },
  {
    path: 'update/:id',
    component: SalesInvoiceUpdateComponent
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
export class SalesInvoiceRoutingModule { }
