import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService } from "./api.service";

import {CompanyResponseDto} from "../model/company-response-dto";
import {CompanyRequestDto} from "../model/company-request-dto";
import { appConst } from "../app.const";
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class CompanyApiService {

  private _apiUrl = environment.apiUrl + appConst.companiesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<CompanyRequestDto, CompanyResponseDto>) {
  }

  getCompanies(httpParams: HttpParams): Observable<CompanyResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getCompany(id: number): Observable<CompanyResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(company: CompanyRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, company);
  }

  update(id: number, company: CompanyRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, company);
  }

  count(): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count');
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
