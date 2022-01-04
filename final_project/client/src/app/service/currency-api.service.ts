import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {CurrencyRequestDto} from "../model/currency/currency-request-dto";
import {CurrencyResponseDto} from "../model/currency/currency-response-dto";

@Injectable({
  providedIn: 'root'
})
export class CurrencyApiService {

  private _apiUrl = environment.apiUrl + appConst.currenciesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<CurrencyRequestDto, CurrencyResponseDto>) {
  }

  getCurrencies(httpParams: HttpParams): Observable<CurrencyResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getCurrency(id: number): Observable<CurrencyResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(currency: CurrencyRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, currency);
  }

  update(id: number, currency: CurrencyRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, currency);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
