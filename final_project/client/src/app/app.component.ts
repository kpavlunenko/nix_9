import {Component} from '@angular/core';
import {TokenStorageService} from "./service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client-angular';
  isLoggedIn = false;
  isRegistration = false;
  constructor(private tokenStorageService: TokenStorageService,
              private _router: Router) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (!this.isLoggedIn && window.location.pathname != '/authentication/register') {
      this._router.navigateByUrl('authentication/login');
    }
  }

  successfulLogin(isLoggedIn:boolean){
    this.isLoggedIn = isLoggedIn;
    this.isRegistration = false;
  }

  isSignUp(isRegistration:boolean){
    this.isLoggedIn = false;
    this.isRegistration = isRegistration;
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
