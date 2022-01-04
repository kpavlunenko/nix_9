import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {PriceRequestDto} from "../model/prices/price-request-dto";
import {PriceResponseDto} from "../model/prices/price-response-dto";

@Injectable({
  providedIn: 'root'
})
export class PriceApiService {

  private _apiUrl = environment.apiUrl + appConst.pricesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<PriceRequestDto, PriceResponseDto>) {
  }

  getPrices(httpParams: HttpParams): Observable<PriceResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getPrice(id: number): Observable<PriceResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(price: PriceRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, price);
  }

  update(id: number, price: PriceRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, price);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
