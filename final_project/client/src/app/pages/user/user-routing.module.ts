import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {UserItemsComponent, UserDetailsComponent, UserUpdateComponent} from "./components";
import {NgxPermissionsGuard} from "ngx-permissions";

const routes: Routes = [
  {
    path: '',
    component: UserItemsComponent,
    canActivate: [NgxPermissionsGuard],
    data: {
      permissions: {
        only: 'ROLE_ADMIN',
        redirectTo: 'dashboard'
      }
    }
  },
  {
    path: 'details/:email',
    component: UserDetailsComponent
  },
  {
    path: 'update/:email',
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
export class UserRoutingModule {
}
