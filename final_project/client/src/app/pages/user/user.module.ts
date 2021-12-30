import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  UserItemsComponent,
  UserDetailsComponent,
  UserUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {MatRadioModule} from "@angular/material/radio";
import {UserRoutingModule} from "./user-routing.module";
import {NgxPermissionsModule} from "ngx-permissions";

@NgModule({
  declarations: [
    UserItemsComponent,
    UserDetailsComponent,
    UserUpdateComponent
  ],
    imports: [
        CommonModule,
        UserRoutingModule,
        ReactiveFormsModule,
        MatRadioModule,
        NgxPermissionsModule
    ]
})
export class UserModule {
}
