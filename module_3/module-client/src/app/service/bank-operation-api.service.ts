import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {BankOperationRequestDto} from "../model/bank-operation-request-dto";
import {BankOperationResponseDto} from "../model/bank-operation-response-dto";

@Injectable({
  providedIn: 'root'
})
export class BankOperationApiService {

  private _apiUrl = environment.apiUrl + appConst.bankOperationsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<BankOperationRequestDto, BankOperationResponseDto>) {
  }

  getBankOperations(httpParams: HttpParams): Observable<BankOperationResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getBankOperation(id: number): Observable<BankOperationResponseDto> {
    return this._apiService.getById(this._apiUrl, id);
  }

  create(bankOperation: BankOperationRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, bankOperation);
  }

  update(id: number, bankOperation: BankOperationRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, bankOperation);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
