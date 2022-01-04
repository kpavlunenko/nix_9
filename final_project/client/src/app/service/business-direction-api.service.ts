import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {BusinessDirectionRequestDto} from "../model/business-direction/business-direction-request-dto";
import {BusinessDirectionResponseDto} from "../model/business-direction/business-direction-response-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BusinessDirectionApiService {

  private _apiUrl = environment.apiUrl + appConst.businessDirectionsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<BusinessDirectionRequestDto, BusinessDirectionResponseDto>) { }

  getBusinessDirections(httpParams: HttpParams): Observable<BusinessDirectionResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getBusinessDirection(id: number): Observable<BusinessDirectionResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(businessDirection: BusinessDirectionRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, businessDirection);
  }

  update(id: number, businessDirection: BusinessDirectionRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, businessDirection);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
