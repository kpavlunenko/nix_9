import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  BusinessDirectionItemsComponent,
  BusinessDirectionDetailsComponent,
  BusinessDirectionNewComponent,
  BusinessDirectionUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {BusinessDirectionRoutingModule} from "./business-direction-routing.module";

@NgModule({
  declarations: [
    BusinessDirectionItemsComponent,
    BusinessDirectionNewComponent,
    BusinessDirectionDetailsComponent,
    BusinessDirectionUpdateComponent
  ],
  imports: [
    CommonModule,
    BusinessDirectionRoutingModule,
    ReactiveFormsModule
  ]
})
export class BusinessDirectionModule {
}
