import {Component, Input, OnInit} from '@angular/core';
import {BankAccountRequestDto} from "../../../../model/bank-account-request-dto";
import {UserShortResponseDto} from "../../../../model/user-short-response-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";
import {UserApiService} from "../../../../service/user-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BankAccountResponseDto} from "../../../../model/bank-account-response-dto";

@Component({
  selector: 'app-bank-account-update',
  templateUrl: './bank-account-update.component.html',
  styleUrls: ['./bank-account-update.component.css']
})
export class BankAccountUpdateComponent implements OnInit {

  id: number = 0;
  userId: string = "";
  bankAccount?: BankAccountRequestDto;
  @Input() bankAccountResponseDto?: Observable<BankAccountResponseDto>;
  users?: UserShortResponseDto[];

  bankAccountForm = new FormGroup({
    name: new FormControl('', Validators.required),
    iban: new FormControl('', Validators.required),
    userId: new FormControl('')
  });

  constructor(private _bankAccountApiService: BankAccountApiService,
              private _userApiService: UserApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.getUsers();
    this.getBankAccount();
    this.parseHttpParams();
  }

  update(): void {
    let bankAccount = this.bankAccountForm.value as BankAccountRequestDto;

    this._bankAccountApiService.update(this.id, bankAccount).subscribe(() => {
      if (this.userId == ""){
        this._router.navigateByUrl('bankAccounts');
      } else {
        this._router.navigateByUrl('users/details/' + this.userId);
      }
    });
  }

  getBankAccount(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.bankAccountResponseDto = this._bankAccountApiService.getBankAccount(this.id);
    this.bankAccountResponseDto.subscribe(bankAccountResponseDto =>
      this.bankAccountForm.setValue({
        name: bankAccountResponseDto.name,
        iban: bankAccountResponseDto.iban,
        userId: bankAccountResponseDto.user.id
      }))
  }

  getUsers(): void {
    this._userApiService.getUsers(new HttpParams()
      .set('sort', 'firstName')
      .set('order', 'asc'))
      .subscribe(users => this.users = users);
  }

  goBack(): void {
    this._location.back();
  }

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['user'] != undefined) {
        this.userId = params['user'];
      }
    })
  }

}
