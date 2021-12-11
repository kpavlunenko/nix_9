import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService } from "./api.service";

import {AgreementResponseDto} from "../model/agreement-response-dto";
import {AgreementRequestDto} from "../model/agreement-request-dto";
import { appConst } from "../app.const";
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class AgreementApiService {

  private _apiUrl = environment.apiUrl + appConst.agreementsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<AgreementRequestDto, AgreementResponseDto>) {
  }

  getAgreements(httpParams: HttpParams): Observable<AgreementResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getAgreement(id: number): Observable<AgreementResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(agreement: AgreementRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, agreement);
  }

  update(id: number, agreement: AgreementRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, agreement);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
