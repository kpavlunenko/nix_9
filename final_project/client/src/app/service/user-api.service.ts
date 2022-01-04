import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {UserRequestDto} from "../model/user/user-request-dto";
import {UserResponseDto} from "../model/user/user-response-dto";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  private _apiUrl = environment.apiUrl + appConst.usersPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<UserRequestDto, UserResponseDto>) {
  }

  getUsers(httpParams: HttpParams): Observable<UserResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getUser(email: string): Observable<UserResponseDto> {
    return this._apiService.getById(this._apiUrl, email);
  }

  update(email: string, user: UserRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, email, user);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
