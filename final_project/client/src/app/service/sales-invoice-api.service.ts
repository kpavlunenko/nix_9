import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {SalesInvoiceResponseDto} from "../model/sales-invoice/sales-invoice-response-dto";
import {SalesInvoiceRequestDto} from "../model/sales-invoice/sales-invoice-request-dto";

@Injectable({
  providedIn: 'root'
})
export class SalesInvoiceApiService {

  private _apiUrl = environment.apiUrl + appConst.salesInvoicePath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<SalesInvoiceRequestDto, SalesInvoiceResponseDto>) {
  }

  getSalesInvoices(httpParams: HttpParams): Observable<SalesInvoiceResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getSalesInvoice(id: number): Observable<SalesInvoiceResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(salesInvoice: SalesInvoiceRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, salesInvoice);
  }

  update(id: number, salesInvoice: SalesInvoiceRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, salesInvoice);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
