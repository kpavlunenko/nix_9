import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {CurrencyRateRequestDto} from "../model/currency-rate/currency-rate-request-dto";
import {CurrencyRateResponseDto} from "../model/currency-rate/currency-rate-response-dto";

@Injectable({
  providedIn: 'root'
})
export class CurrencyRateApiService {

  private _apiUrl = environment.apiUrl + appConst.currencyRatesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<CurrencyRateRequestDto, CurrencyRateResponseDto>) {
  }

  getCurrencyRates(httpParams: HttpParams): Observable<CurrencyRateResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getCurrencyRate(id: number): Observable<CurrencyRateResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(currency: CurrencyRateRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, currency);
  }

  update(id: number, currency: CurrencyRateRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, currency);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
