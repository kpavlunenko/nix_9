import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {catchError, Observable} from "rxjs";
import {PriceRequestDto} from "../model/prices/price-request-dto";
import {PriceResponseDto} from "../model/prices/price-response-dto";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PriceApiService {

  private _apiUrl = environment.apiUrl + appConst.pricesPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<PriceRequestDto, PriceResponseDto>) {
  }

  getPrices(httpParams: HttpParams): Observable<PriceResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getPrice(id: number): Observable<PriceResponseDto> {
    return this._apiService.getById(this._apiUrl, id);
  }

  getNomenclaturePrice(httpParams: HttpParams): Observable<PriceResponseDto> {
    return this._http.get(this._apiUrl + '/' + 'nomenclaturePrice', {
      params: httpParams,
      headers: new HttpHeaders({})
    }).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          error: error.error.error,
          message: error.error.message,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  create(price: PriceRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, price);
  }

  update(id: number, price: PriceRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, price);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
