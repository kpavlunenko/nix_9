import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CounterpartyItemsComponent, CounterpartyDetailsComponent, CounterpartyNewComponent, CounterpartyUpdateComponent } from "./components";
import { CounterpartyRoutingModule } from "./counterparty-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import {NgxPermissionsModule} from "ngx-permissions";

@NgModule({
  declarations: [
    CounterpartyItemsComponent,
    CounterpartyDetailsComponent,
    CounterpartyNewComponent,
    CounterpartyUpdateComponent
  ],
    imports: [
        CommonModule,
        CounterpartyRoutingModule,
        ReactiveFormsModule,
        NgxPermissionsModule
    ]
})
export class CounterpartyModule {
}
