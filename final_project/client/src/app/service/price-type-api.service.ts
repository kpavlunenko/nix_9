import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {PriceTypeRequestDto} from "../model/price-type/price-type-request-dto";
import {PriceTypeResponseDto} from "../model/price-type/price-type-response-dto";

@Injectable({
  providedIn: 'root'
})
export class PriceTypeApiService {

  private _apiUrl = environment.apiUrl + appConst.priceTypesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<PriceTypeRequestDto, PriceTypeResponseDto>) {
  }

  getPriceTypes(httpParams: HttpParams): Observable<PriceTypeResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getPriceType(id: number): Observable<PriceTypeResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(priceType: PriceTypeRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, priceType);
  }

  update(id: number, priceType: PriceTypeRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, priceType);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
