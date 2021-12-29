import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {TokenStorageService} from "../../../../service/token-storage.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRequestDto} from "../../../../model/login-request-dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  roles: string[] = [];

  @Output() successfulLogin = new EventEmitter<boolean>();
  @Output() isSignUp = new EventEmitter<boolean>();
  loginRequest?: LoginRequestDto;
  loginForm = new FormGroup({
    username: new FormControl('', Validators.email),
    password: new FormControl(''),
  });

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private _router: Router) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  goSignUp() {
    this._router.navigateByUrl('/authentication/register');
    this.isSignUp.emit(true);
  }

  login() {
    let loginRequest = this.loginForm.value as LoginRequestDto;
    this.authService.login(loginRequest).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.successfulLogin.emit(true);
        this._router.navigateByUrl('dashboard');
      }
    );
  }
}
