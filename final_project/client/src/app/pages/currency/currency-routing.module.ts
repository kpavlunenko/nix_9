import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { CurrencyItemsComponent, CurrencyDetailsComponent, CurrencyNewComponent, CurrencyUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: CurrencyItemsComponent
  },
  {
    path: 'new',
    component: CurrencyNewComponent
  },
  {
    path: 'details/:id',
    component: CurrencyDetailsComponent
  },
  {
    path: 'update/:id',
    component: CurrencyUpdateComponent
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
export class CurrencyRoutingModule { }
