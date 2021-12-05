import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyItemsComponent, CompanyDetailsComponent, CompanyNewComponent } from "./components";
import { CompanyRoutingModule } from "./company-routing.module";
import { ReactiveFormsModule } from "@angular/forms";


@NgModule({
  declarations: [
    CompanyItemsComponent,
    CompanyNewComponent,
    CompanyDetailsComponent],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    ReactiveFormsModule
  ]
})
export class CompanyModule {
}
