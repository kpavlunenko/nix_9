import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BankAccountResponseDto} from "../../../../model/bank-account-response-dto";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";

@Component({
  selector: 'app-bank-account-details',
  templateUrl: './bank-account-details.component.html',
  styleUrls: ['./bank-account-details.component.css']
})
export class BankAccountDetailsComponent implements OnInit {

  id?: number;
  userId: string = "";

  @Input() bankAccount?: BankAccountResponseDto;

  constructor(private route: ActivatedRoute,
              private _bankAccountApiService: BankAccountApiService,
              private _location: Location,
              private _route: ActivatedRoute,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getBankAccount();
    this.parseHttpParams();
  }

  getBankAccount(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._bankAccountApiService.getBankAccount(this.id)
      .subscribe(bankAccount => this.bankAccount = bankAccount);
  }

  goBack(): void {
    this._location.back();
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

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['user'] != undefined) {
        this.userId = params['user'];
      }
    })
  }

}
