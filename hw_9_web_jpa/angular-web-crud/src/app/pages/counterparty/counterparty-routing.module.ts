import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { CounterpartyItemsComponent, CounterpartyDetailsComponent, CounterpartyNewComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: CounterpartyItemsComponent
  },
  {
    path: 'new',
    component: CounterpartyNewComponent
  },
  {
    path: 'details/:id',
    component: CounterpartyDetailsComponent
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
export class CounterpartyRoutingModule { }
