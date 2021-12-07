import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyItemsComponent, CompanyDetailsComponent, CompanyNewComponent, CompanyUpdateComponent } from "./components";
import { CompanyRoutingModule } from "./company-routing.module";
import { ReactiveFormsModule } from "@angular/forms";


@NgModule({
    declarations: [
        CompanyItemsComponent,
        CompanyNewComponent,
        CompanyDetailsComponent,
        CompanyUpdateComponent
    ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    ReactiveFormsModule
  ]
})
export class CompanyModule {
}
