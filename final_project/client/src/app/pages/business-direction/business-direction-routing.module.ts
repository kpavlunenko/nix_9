import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {
  BusinessDirectionItemsComponent,
  BusinessDirectionDetailsComponent,
  BusinessDirectionNewComponent,
  BusinessDirectionUpdateComponent
} from "./components";

const routes: Routes = [
  {
    path: '',
    component: BusinessDirectionItemsComponent
  },
  {
    path: 'new',
    component: BusinessDirectionNewComponent
  },
  {
    path: 'details/:id',
    component: BusinessDirectionDetailsComponent
  },
  {
    path: 'update/:id',
    component: BusinessDirectionUpdateComponent
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
export class BusinessDirectionRoutingModule {
}
