import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankAccountItemsComponent, BankAccountDetailsComponent, BankAccountNewComponent, BankAccountUpdateComponent } from "./components";
import { BankAccountRoutingModule } from "./bank-account-routing.module";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [BankAccountItemsComponent, BankAccountDetailsComponent, BankAccountNewComponent, BankAccountUpdateComponent],
  imports: [
    CommonModule,
    BankAccountRoutingModule,
    ReactiveFormsModule
  ]
})
export class BankAccountModule { }
