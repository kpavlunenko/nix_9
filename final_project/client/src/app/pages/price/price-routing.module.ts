import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { PriceItemsComponent, PriceDetailsComponent, PriceNewComponent, PriceUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: PriceItemsComponent
  },
  {
    path: 'new',
    component: PriceNewComponent
  },
  {
    path: 'details/:id',
    component: PriceDetailsComponent
  },
  {
    path: 'update/:id',
    component: PriceUpdateComponent
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
export class PriceRoutingModule { }
