import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import {UserDetailsComponent, UserItemsComponent, UserNewComponent, UserUpdateComponent} from "./components";

const routes: Routes = [
  {
    path: '',
    component: UserItemsComponent
  },
  {
    path: 'new',
    component: UserNewComponent
  },
  {
    path: 'details/:id',
    component: UserDetailsComponent
  },
  {
    path: 'update/:id',
    component: UserUpdateComponent
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
export class UserRoutingModule { }
