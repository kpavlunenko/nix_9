import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService } from "./api.service";

import { CounterpartyResponseDto} from "../model/counterparty/counterparty-response-dto";
import { CounterpartyRequestDto} from "../model/counterparty/counterparty-request-dto";
import { appConst } from "../app.const";
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class CounterpartyApiService {

  private _apiUrl = environment.apiUrl + appConst.counterpartiesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<CounterpartyRequestDto, CounterpartyResponseDto>) { }

  getCounterparties(httpParams: HttpParams): Observable<CounterpartyResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getCounterparty(id: number): Observable<CounterpartyResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(counterparty: CounterpartyRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, counterparty);
  }

  update(id: number, counterparty: CounterpartyRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, counterparty);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
