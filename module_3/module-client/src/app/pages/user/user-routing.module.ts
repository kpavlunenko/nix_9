import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { UserItemsComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: UserItemsComponent
  }
  // },
  // {
  //   path: 'new',
  //   component: AgreementNewComponent
  // },
  // {
  //   path: 'details/:id',
  //   component: AgreementDetailsComponent
  // },
  // {
  //   path: 'update/:id',
  //   component: AgreementUpdateComponent
  // }
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
