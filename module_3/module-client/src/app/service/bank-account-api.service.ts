import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {BankAccountRequestDto} from "../model/bank-account-request-dto";
import {BankAccountResponseDto} from "../model/bank-account-response-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BankAccountApiService {

  private _apiUrl = environment.apiUrl + appConst.bankAccountsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<BankAccountRequestDto, BankAccountResponseDto>) { }

  getBankAccounts(httpParams: HttpParams): Observable<BankAccountResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getBankAccount(id: number): Observable<BankAccountResponseDto> {
    return this._apiService.getById(this._apiUrl, id);
  }

  create(user: BankAccountRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, user);
  }

  update(id: number, user: BankAccountRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, user);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  balance(id: number, httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/balance/' + id, httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
