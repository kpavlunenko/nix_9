import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  RegisterComponent,
  LoginComponent
} from "./components";
import {AuthenticationRoutingModule} from "./authentication-routing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {TableModule} from "../table/table.module";


@NgModule({
    declarations: [
        RegisterComponent,
        LoginComponent
    ],
    exports: [
        LoginComponent,
        RegisterComponent
    ],
    imports: [
        CommonModule,
        AuthenticationRoutingModule,
        ReactiveFormsModule,
        TableModule
    ]
})
export class AuthenticationModule {
}
