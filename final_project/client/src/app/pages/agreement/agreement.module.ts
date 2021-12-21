import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  AgreementItemsComponent,
  AgreementDetailsComponent,
  AgreementNewComponent,
  AgreementUpdateComponent
} from "./components";
import {AgreementRoutingModule} from "./agreement-routing.module";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AgreementItemsComponent,
    AgreementNewComponent,
    AgreementDetailsComponent,
    AgreementUpdateComponent
  ],
  imports: [
    CommonModule,
    AgreementRoutingModule,
    ReactiveFormsModule
  ]
})
export class AgreementModule {
}
