import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  CategoryItemsComponent,
  CategoryDetailsComponent,
  CategoryNewComponent,
  CategoryUpdateComponent
} from "./components";
import {CategoryRoutingModule} from "./category-routing.module";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [CategoryItemsComponent, CategoryDetailsComponent, CategoryNewComponent, CategoryUpdateComponent],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    ReactiveFormsModule
  ]
})
export class CategoryModule {
}
