import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from "@angular/router";
import { NomenclatureItemsComponent, NomenclatureDetailsComponent, NomenclatureNewComponent, NomenclatureUpdateComponent } from "./components";

const routes: Routes = [
  {
    path: '',
    component: NomenclatureItemsComponent
  },
  {
    path: 'new',
    component: NomenclatureNewComponent
  },
  {
    path: 'details/:id',
    component: NomenclatureDetailsComponent
  },
  {
    path: 'update/:id',
    component: NomenclatureUpdateComponent
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
export class NomenclatureRoutingModule { }
