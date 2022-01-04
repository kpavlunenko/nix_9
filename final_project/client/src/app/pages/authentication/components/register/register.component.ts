import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignUpRequestDto} from "../../../../model/auth/sign-up-request-dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isSuccessful = false;
  signUpRequest?: SignUpRequestDto;
  @Output() isSignUp = new EventEmitter<boolean>();

  signUpForm = new FormGroup({
    email: new FormControl('', Validators.email),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl('')
  });

  constructor(private authService: AuthService,
              private _router: Router) {
  }

  ngOnInit(): void {
  }

  goToLogin() {
    this._router.navigateByUrl('/authentication/login');
    this.isSignUp.emit(false);
  }

  signUp() {
    let signUpRequest = this.signUpForm.value as SignUpRequestDto;
    this.authService.register(signUpRequest).subscribe(
      data => {
        this.isSuccessful = true;
      }
    );
  }
}
