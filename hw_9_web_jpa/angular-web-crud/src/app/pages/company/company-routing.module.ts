import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { CompanyItemsComponent, CompanyDetailsComponent, CompanyNewComponent, CompanyUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: CompanyItemsComponent
  },
  {
    path: 'new',
    component: CompanyNewComponent
  },
  {
    path: 'details/:id',
    component: CompanyDetailsComponent
  },
  {
    path: 'update/:id',
    component: CompanyUpdateComponent
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
export class CompanyRoutingModule { }
