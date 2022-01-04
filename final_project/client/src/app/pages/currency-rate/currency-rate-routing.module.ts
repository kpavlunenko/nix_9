import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { CurrencyRateItemsComponent, CurrencyRateDetailsComponent, CurrencyRateNewComponent, CurrencyRateUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: CurrencyRateItemsComponent
  },
  {
    path: 'new',
    component: CurrencyRateNewComponent
  },
  {
    path: 'details/:id',
    component: CurrencyRateDetailsComponent
  },
  {
    path: 'update/:id',
    component: CurrencyRateUpdateComponent
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
export class CurrencyRateRoutingModule { }
