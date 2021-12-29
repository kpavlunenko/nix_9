import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { UserItemsComponent, UserDetailsComponent, UserUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: UserItemsComponent
  },
  {
    path: 'details',
    component: UserDetailsComponent
  },
  {
    path: 'update',
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
