import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import {CategoryDetailsComponent, CategoryItemsComponent, CategoryNewComponent, CategoryUpdateComponent} from "./components";

const routes: Routes = [
  {
    path: '',
    component: CategoryItemsComponent
  },
  {
    path: 'new',
    component: CategoryNewComponent
  },
  {
    path: 'details/:id',
    component: CategoryDetailsComponent
  },
  {
    path: 'update/:id',
    component: CategoryUpdateComponent
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
export class CategoryRoutingModule { }
