import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {ErrorDialogComponent} from './pages/error-dialog/error-dialog.component';
import {ErrorDialogService} from "./service/error-dialog.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDialogModule} from "@angular/material/dialog";
import {authInterceptorProviders} from "./interceptor/auth.interceptor";

import {
  AgreementModule,
  CompanyModule,
  CounterpartyModule,
  BusinessDirectionModule,
  NomenclatureModule,
  CurrencyModule,
  CurrencyRateModule,
  PriceModule,
  PriceTypeModule,
  AuthenticationModule,
  DashboardModule,
  SalesInvoiceModule,
  PurchaseInvoiceModule,
  UserModule
} from "./pages";
import {NgxPermissionsModule} from "ngx-permissions";

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
    NgxPermissionsModule.forRoot(),
    CompanyModule,
    CurrencyRateModule,
    CounterpartyModule,
    PriceModule,
    PriceTypeModule,
    AgreementModule,
    BusinessDirectionModule,
    NomenclatureModule,
    CurrencyModule,
    AuthenticationModule,
    DashboardModule,
    SalesInvoiceModule,
    PurchaseInvoiceModule,
    UserModule
  ],
  providers: [ErrorDialogService,
    authInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [ErrorDialogComponent]
})
export class AppModule {
}
