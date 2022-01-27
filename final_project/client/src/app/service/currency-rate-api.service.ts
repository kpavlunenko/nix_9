import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {catchError, Observable} from "rxjs";
import {CurrencyRateRequestDto} from "../model/currency-rate/currency-rate-request-dto";
import {CurrencyRateResponseDto} from "../model/currency-rate/currency-rate-response-dto";
import {map} from "rxjs/operators";

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

  create(currencyRate: CurrencyRateRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, currencyRate);
  }

  update(id: number, currencyRate: CurrencyRateRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, currencyRate);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  getRateOnDate(httpParams: HttpParams): Observable<CurrencyRateResponseDto> {
    return this._http.get(this._apiUrl + '/' + 'rate_on_date', {
      params: httpParams,
      headers: new HttpHeaders({})
    }).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          error: error.error.error,
          message: error.error.message,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
