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
import {MatRadioModule} from "@angular/material/radio";

@NgModule({
  declarations: [BankOperationItemsComponent, BankOperationDetailsComponent, BankOperationNewComponent, BankOperationUpdateComponent],
  imports: [
    CommonModule,
    BankOperationRoutingModule,
    ReactiveFormsModule,
    MatRadioModule
  ]
})
export class BankOperationModule {
}
