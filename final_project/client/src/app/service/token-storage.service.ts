import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private cookieService: CookieService) {
  }

  signOut() {
    this.cookieService.deleteAll('/');
  }

  public saveToken(token: string) {
    this.cookieService.delete(TOKEN_KEY);
    this.cookieService.set(TOKEN_KEY, token,1,'/');
  }

  public getToken(): string {
    return this.cookieService.get(TOKEN_KEY);
  }

  public saveUser(user: any) {
    this.cookieService.delete(USER_KEY);
    this.cookieService.set(USER_KEY, JSON.stringify(user),1,'/');
  }

  public getUser() {
    return JSON.parse(this.cookieService.get(USER_KEY));
  }
}
