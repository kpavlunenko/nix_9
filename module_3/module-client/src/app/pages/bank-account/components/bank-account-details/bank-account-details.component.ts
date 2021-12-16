import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BankAccountResponseDto} from "../../../../model/bank-account-response-dto";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-bank-account-details',
  templateUrl: './bank-account-details.component.html',
  styleUrls: ['./bank-account-details.component.css']
})
export class BankAccountDetailsComponent implements OnInit {

  id?: number;
  userId: string = "";
  balance: number = 0;

  @Input() bankAccount?: BankAccountResponseDto;

  constructor(private route: ActivatedRoute,
              private _bankAccountApiService: BankAccountApiService,
              private _location: Location,
              private _route: ActivatedRoute,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getBankAccount();
    this.getBalanceOfBankAccount();
    this.parseHttpParams();
  }

  getBankAccount(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._bankAccountApiService.getBankAccount(this.id)
      .subscribe(bankAccount => this.bankAccount = bankAccount);
  }

  getBalanceOfBankAccount(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._bankAccountApiService.balance(this.id, new HttpParams())
      .subscribe(balance => this.balance = balance);
  }

  goBack(): void {
    if (this.userId != "") {
      this._router.navigate(['/users/details/' + this.userId]);
    } else  {
      this._router.navigate(['/bankAccounts/update/' + this.id]);
    }
  }

  updateBankAccount(): void {
    if (this.userId != "") {
      this._router.navigate(['/bankAccounts/update/' + this.id],{
        queryParams: { user: this.userId}
      });
    } else  {
      this._router.navigate(['/bankAccounts/update/' + this.id]);
    }
  }

  goToOperations(): void {
    this._router.navigate(['/bankOperations'],{
      queryParams: {bankAccount: this.id, user: this.userId}
    });
  }

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['user'] != undefined) {
        this.userId = params['user'];
      }
    })
  }

}
