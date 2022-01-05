import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  NomenclatureItemsComponent,
  NomenclatureNewComponent,
  NomenclatureDetailsComponent,
  NomenclatureUpdateComponent
} from "./components";
import {ReactiveFormsModule} from "@angular/forms";
import {NomenclatureRoutingModule} from "./nomenclature-routing.module";
import {MatRadioModule} from "@angular/material/radio";
import {NgxPermissionsModule} from "ngx-permissions";

@NgModule({
  declarations: [
    NomenclatureItemsComponent,
    NomenclatureNewComponent,
    NomenclatureDetailsComponent,
    NomenclatureUpdateComponent
  ],
    imports: [
        CommonModule,
        NomenclatureRoutingModule,
        ReactiveFormsModule,
        MatRadioModule,
        NgxPermissionsModule
    ]
})
export class NomenclatureModule {
}
