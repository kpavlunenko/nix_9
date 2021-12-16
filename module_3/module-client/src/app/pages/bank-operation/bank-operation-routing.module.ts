import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import {BankOperationDetailsComponent, BankOperationItemsComponent, BankOperationNewComponent, BankOperationUpdateComponent} from "./components";

const routes: Routes = [
  {
    path: '',
    component: BankOperationItemsComponent
  },
  {
    path: 'new',
    component: BankOperationNewComponent
  },
  {
    path: 'details/:id',
    component: BankOperationDetailsComponent
  },
  {
    path: 'update/:id',
    component: BankOperationUpdateComponent
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
export class BankOperationRoutingModule { }
