import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, Observable} from 'rxjs';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {map} from "rxjs/operators";
import {ErrorDialogService} from "./error-dialog.service";
import {SignUpRequestDto} from "../model/auth/sign-up-request-dto";
import {LoginRequestDto} from "../model/auth/login-request-dto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _apiUrl = environment.apiUrl + appConst.authenticationPath;

  constructor(private http: HttpClient,
              public errorDialogService: ErrorDialogService) {
  }

  login(loginRequest: LoginRequestDto): Observable<any> {
    return this.http.post(this._apiUrl + '/signin', {
      username: loginRequest.username,
      password: loginRequest.password
    }, httpOptions).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        if (error.error.error == null) {
          data = {
            error: "",
            message: 'problem with connect to server',
            status: error.status
          };
        } else {
          data = {
            error: error.error.error,
            message: error.error.message,
            status: error.status
          };
        }
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }

  register(signUpRequest: SignUpRequestDto): Observable<any> {
    return this.http.post(this._apiUrl + '/signup', signUpRequest, httpOptions).pipe(
      map((res: any) => {
        return res
      }),
      catchError(error => {
        let data = {};
        if (error.error.error == null) {
          data = {
            error: "",
            message: 'problem with connect to server',
            status: error.status
          };
        } else {
          data = {
            error: error.error.error,
            message: error.error.message,
            status: error.status
          };
        }
        // @ts-ignore
        throw this.errorDialogService.openDialog(data);
      })
    );
  }
}
