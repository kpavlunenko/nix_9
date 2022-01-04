import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { PriceTypeItemsComponent, PriceTypeDetailsComponent, PriceTypeNewComponent, PriceTypeUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: PriceTypeItemsComponent
  },
  {
    path: 'new',
    component: PriceTypeNewComponent
  },
  {
    path: 'details/:id',
    component: PriceTypeDetailsComponent
  },
  {
    path: 'update/:id',
    component: PriceTypeUpdateComponent
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
export class PriceTypeRoutingModule { }
