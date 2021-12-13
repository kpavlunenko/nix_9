import { Component, OnInit } from '@angular/core';
import { Location } from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";

import {UserRequestDto} from "../../../../model/user-request-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserApiService} from "../../../../service/user-api.service";

@Component({
  selector: 'app-user-new',
  templateUrl: './user-new.component.html',
  styleUrls: ['./user-new.component.css']
})
export class UserNewComponent implements OnInit {

  user?: UserRequestDto;

  userForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    phone: new FormControl('', Validators.required)
  });

  constructor(private _userApiService: UserApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
  }

  create(): void {
    let user = this.userForm.value as UserRequestDto;

    this._userApiService.create(user).subscribe(() => {
      this._router.navigateByUrl('users');
    });
  }

  goBack(): void {
    this._location.back();
  }

}
