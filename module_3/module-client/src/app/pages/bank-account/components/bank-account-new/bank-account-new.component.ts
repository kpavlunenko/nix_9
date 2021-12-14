import { Component, OnInit } from '@angular/core';
import {UserRequestDto} from "../../../../model/user-request-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserApiService} from "../../../../service/user-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BankAccountRequestDto} from "../../../../model/bank-account-request-dto";
import {UserShortResponseDto} from "../../../../model/user-short-response-dto";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-bank-account-new',
  templateUrl: './bank-account-new.component.html',
  styleUrls: ['./bank-account-new.component.css']
})
export class BankAccountNewComponent implements OnInit {

  bankAccount?: BankAccountRequestDto;
  users?: UserShortResponseDto[];
  userId: string = "";

  bankAccountForm = new FormGroup({
    name: new FormControl('', Validators.required),
    iban: new FormControl('', Validators.required),
    userId: new FormControl(this.userId)
  });

  constructor(private _bankAccountApiService: BankAccountApiService,
              private _userApiService: UserApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.parseHttpParams();
    this.getUsers();
  }

  create(): void {
    let bankAccount = this.bankAccountForm.value as BankAccountRequestDto;

    this._bankAccountApiService.create(bankAccount).subscribe(() => {
      if (this.userId == ""){
        this._router.navigateByUrl('bankAccounts');
      } else {
        this._router.navigateByUrl('users/details/' + this.userId);
      }
    });
  }

  getUsers(): void {
    this._userApiService.getUsers(new HttpParams()
      .set('sort', 'firstName')
      .set('order', 'asc'))
      .subscribe(users => this.users = users);
  }

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['user'] != undefined) {
        this.userId = params['user'];
      }
      this.bankAccountForm.setValue({
        name: '',
        iban: '',
        userId: this.userId
      })
    })
  }

  goBack(): void {
    this._location.back();
  }

}
