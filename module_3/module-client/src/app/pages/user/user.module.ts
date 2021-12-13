import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserItemsComponent } from "./components";
import { UserRoutingModule } from "./user-routing.module";


@NgModule({
  declarations: [UserItemsComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
