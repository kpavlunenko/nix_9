import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";

import { CompanyResponseDto } from "../model/company-response-dto";
import { MessageService } from '../message.service';
import { CompanyRequestDto } from "../model/company-request-dto";

@Injectable({
  providedIn: 'root'
})
export class CompanyApiService {

  private _companiesUrl = 'http://localhost:8080/api/companies';

  constructor(private _http: HttpClient,
              private messageService: MessageService) {
  }

  getCompanies(): Observable<CompanyResponseDto[]> {
    return this._http.get<CompanyResponseDto[]>(this._companiesUrl)
      .pipe(
        tap(_ => this.log('fetched heroes')),
        catchError(this.handleError<CompanyResponseDto[]>('getHeroes', []))
      );
  }

  getCompany(id: number): Observable<CompanyResponseDto> {
    return this._http.get<CompanyResponseDto>(this._companiesUrl + '/' + id, this._getOptions()).pipe(
      map((company: any) => {
        return company
      })
    );
  }

  create(company: CompanyRequestDto): Observable<boolean> {
    return this._http.post(this._companiesUrl, company, this._getOptions()).pipe(
      map((result: any) => {
        return result.data
      })
    );
  }

  deleteById(id: number): Observable<boolean> {
    return this._http.delete(this._companiesUrl + '/' + id, this._getOptions()).pipe(
      map((result: any) => {
        return result
      })
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }

  private _getOptions(): any {
    return {
      headers: new HttpHeaders({})
    };
  }
}
