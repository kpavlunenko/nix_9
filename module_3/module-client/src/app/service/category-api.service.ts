import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {CategoryRequestDto} from "../model/category-request-dto";
import {CategoryResponseDto} from "../model/category-response-dto";

@Injectable({
  providedIn: 'root'
})
export class CategoryApiService {

  private _apiUrl = environment.apiUrl + appConst.categoriesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<CategoryRequestDto, CategoryResponseDto>) {
  }

  getCategories(httpParams: HttpParams): Observable<CategoryResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getCategory(id: number): Observable<CategoryResponseDto> {
    return this._apiService.getById(this._apiUrl, id);
  }

  create(category: CategoryRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, category);
  }

  update(id: number, category: CategoryRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, category);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
