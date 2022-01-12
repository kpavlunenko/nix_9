import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {PurchaseInvoiceRequestDto} from "../model/purchase-invoice/purchase-invoice-request-dto";
import {PurchaseInvoiceResponseDto} from "../model/purchase-invoice/purchase-invoice-response-dto";

@Injectable({
  providedIn: 'root'
})
export class PurchaseInvoiceApiService {

  private _apiUrl = environment.apiUrl + appConst.purchaseInvoicePath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<PurchaseInvoiceRequestDto, PurchaseInvoiceResponseDto>) {
  }

  getPurchaseInvoices(httpParams: HttpParams): Observable<PurchaseInvoiceResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getPurchaseInvoice(id: number): Observable<PurchaseInvoiceResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(purchaseInvoice: PurchaseInvoiceRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, purchaseInvoice);
  }

  update(id: number, purchaseInvoice: PurchaseInvoiceRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, purchaseInvoice);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
