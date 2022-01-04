import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {NomenclatureRequestDto} from "../model/nomenclature/nomenclature-request-dto";
import {NomenclatureResponseDto} from "../model/nomenclature/nomenclature-response-dto";

@Injectable({
  providedIn: 'root'
})
export class NomenclatureApiService {

  private _apiUrl = environment.apiUrl + appConst.nomenclaturesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<NomenclatureRequestDto, NomenclatureResponseDto>) {
  }

  getNomenclatures(httpParams: HttpParams): Observable<NomenclatureResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getNomenclature(id: number): Observable<NomenclatureResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(nomenclature: NomenclatureRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, nomenclature);
  }

  update(id: number, nomenclature: NomenclatureRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, nomenclature);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
