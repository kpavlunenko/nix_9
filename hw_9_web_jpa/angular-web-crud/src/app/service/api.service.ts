import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {catchError, Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { ErrorDialogService } from "../pages/error-dialog/error-dialog.service";

@Injectable({
  providedIn: 'root'
})
export class ApiService<REQUEST_DTO, RESPONSE_DTO> {

  constructor(private _http: HttpClient,
              public errorDialogService: ErrorDialogService) { }

  getAll(apiUrl: string, httpParams: HttpParams): Observable<RESPONSE_DTO[]> {
    return this._http.get(apiUrl,{
      params: httpParams,
      headers: new HttpHeaders({})
    }).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          message: error.error.error,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  getById(apiUrl: string, id: number): Observable<RESPONSE_DTO> {
    return this._http.get(apiUrl + '/' + id, this._getOptions()).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          message: error.error.error,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  count(apiUrl: string): Observable<number> {
    return this._http.get<number>(apiUrl, this._getOptions()).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          message: error.error.error,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  deleteById(apiUrl: string, id: number): Observable<boolean> {
    return this._http.delete(apiUrl + '/' + id, this._getOptions()).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          message: error.error.v,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  create(apiUrl: string, dto: REQUEST_DTO): Observable<boolean> {
    return this._http.post(apiUrl, dto, this._getOptions()).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          message: error.error.error,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  update(apiUrl: string, id: number, dto: REQUEST_DTO): Observable<boolean> {
    return this._http.put(apiUrl + '/' + id, dto, this._getOptions()).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        data = {
          message: error.error.error,
          status: error.status
        };
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  private _getOptions(): any {
    return {
      headers: new HttpHeaders({})
    };
  }
}
