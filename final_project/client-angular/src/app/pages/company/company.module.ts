import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  CompanyItemsComponent,
  CompanyDetailsComponent,
  CompanyNewComponent,
  CompanyUpdateComponent
} from "./components";
import {CompanyRoutingModule} from "./company-routing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {TableModule} from "../table/table.module";


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
        ReactiveFormsModule,
        TableModule
    ]
})
export class CompanyModule {
}
