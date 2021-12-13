import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserItemsComponent, UserDetailsComponent, UserNewComponent, UserUpdateComponent } from "./components";
import { UserRoutingModule } from "./user-routing.module";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [UserItemsComponent, UserDetailsComponent, UserNewComponent, UserUpdateComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
