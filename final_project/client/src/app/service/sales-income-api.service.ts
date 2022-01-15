import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {SalesIncomeResponseDto} from "../model/sales-income/sales-income-response-dto";
import {SalesIncomeRequestDto} from "../model/sales-income/sales-income-request-dto";

@Injectable({
  providedIn: 'root'
})
export class SalesIncomeApiService {

  private _apiUrl = environment.apiUrl + appConst.salesIncomePath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<SalesIncomeRequestDto, SalesIncomeResponseDto>) {
  }

  getSalesIncomes(httpParams: HttpParams): Observable<SalesIncomeResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

}
