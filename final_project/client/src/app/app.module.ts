import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {ErrorDialogComponent} from './pages/error-dialog/error-dialog.component';
import {ErrorDialogService} from "./service/error-dialog.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDialogModule} from "@angular/material/dialog";

import {
  AgreementModule,
  CompanyModule,
  CounterpartyModule,
  BusinessDirectionModule,
  NomenclatureModule,
  CurrencyModule
} from "./pages";

@NgModule({
  declarations: [
    AppComponent,
    ErrorDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    CompanyModule,
    CounterpartyModule,
    AgreementModule,
    BusinessDirectionModule,
    NomenclatureModule,
    CurrencyModule
  ],
  providers: [ErrorDialogService],
  bootstrap: [AppComponent],
  entryComponents: [ErrorDialogComponent]
})
export class AppModule {
}
