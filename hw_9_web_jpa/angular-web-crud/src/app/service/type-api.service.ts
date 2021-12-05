import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";

import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root'
})
export class TypeApiService {

  private _typesUrl = 'http://localhost:8080/api/types';

  constructor(private _http: HttpClient,
              private messageService: MessageService) { }

  getTypes(nameOfType: string): Observable<string[]> {
    return this._http.get<string[]>(this._typesUrl + '/' + nameOfType)
      .pipe(
        tap(_ => this.log('fetched heroes')),
        catchError(this.handleError<string[]>('getHeroes', []))
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

}
