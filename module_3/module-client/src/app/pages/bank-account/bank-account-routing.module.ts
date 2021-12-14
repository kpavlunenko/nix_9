import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import {BankAccountDetailsComponent, BankAccountItemsComponent, BankAccountNewComponent, BankAccountUpdateComponent} from "./components";

const routes: Routes = [
  {
    path: '',
    component: BankAccountItemsComponent
  },
  {
    path: 'new',
    component: BankAccountNewComponent
  },
  {
    path: 'details/:id',
    component: BankAccountDetailsComponent
  },
  {
    path: 'update/:id',
    component: BankAccountUpdateComponent
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
export class BankAccountRoutingModule { }
