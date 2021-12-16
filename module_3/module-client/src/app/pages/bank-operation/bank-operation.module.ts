import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  BankOperationItemsComponent,
  BankOperationDetailsComponent,
  BankOperationNewComponent,
  BankOperationUpdateComponent
} from "./components";
import {BankOperationRoutingModule} from "./bank-operation-routing.module";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [BankOperationItemsComponent, BankOperationDetailsComponent, BankOperationNewComponent, BankOperationUpdateComponent],
  imports: [
    CommonModule,
    BankOperationRoutingModule,
    ReactiveFormsModule
  ]
})
export class BankOperationModule {
}
