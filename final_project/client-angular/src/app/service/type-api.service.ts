import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class TypeApiService {

  private _apiUrl = environment.apiUrl + appConst.typesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<string, string>) {
  }

  getTypes(nameOfType: string): Observable<string[]> {
    return this._apiService.getAll(this._apiUrl + '/' + nameOfType, new HttpParams());
  }
}
