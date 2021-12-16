import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BankOperationRequestDto} from "../../../../model/bank-operation-request-dto";
import {
  BankOperationType,
  IncomeBankOperationTypeMapping,
  OutcomeBankOperationTypeMapping
} from "../../../../model/bank-operation-type";
import {BankOperationApiService} from "../../../../service/bank-operation-api.service";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";
import {HttpParams} from "@angular/common/http";
import {BankAccountShortResponseDto} from "../../../../model/bank-account-short-response-dto";
import {CategoryApiService} from "../../../../service/category-api.service";
import {CategoryResponseDto} from "../../../../model/category-response-dto";

@Component({
  selector: 'app-bank-operation-new',
  templateUrl: './bank-operation-new.component.html',
  styleUrls: ['./bank-operation-new.component.css']
})
export class BankOperationNewComponent implements OnInit {

  bankOperation?: BankOperationRequestDto;
  IncomeBankOperationTypeMapping = IncomeBankOperationTypeMapping;
  OutcomeBankOperationTypeMapping = OutcomeBankOperationTypeMapping;
  operationType: string = 'INCOME';
  bankAccounts?: BankAccountShortResponseDto[];
  categories?: CategoryResponseDto[];
  bankAccountId: string = "";
  userId: string = "";

  bankOperationForm = new FormGroup({
    bankAccountId: new FormControl(''),
    operationType: new FormControl(''),
    bankOperationType: new FormControl(''),
    categoryId: new FormControl(''),
    recipientBankAccountId: new FormControl(''),
    amount: new FormControl(''),
  });

  constructor(private _bankOperationApiService: BankOperationApiService,
              private _bankAccountApiService: BankAccountApiService,
              private _categoryApiService: CategoryApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.parseHttpParams();
    this.getBankAccounts();
  }

  getBankAccounts(): void {
    this._bankAccountApiService.getBankAccounts(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(bankAccounts => this.bankAccounts = bankAccounts);
  }

  changeBankOperationType(bankOperationType: string): void {
    this.getCategories(bankOperationType);
  }

  getCategories(bankOperationType: string): void {
      this._categoryApiService.getCategories(new HttpParams().set('bankOperationType', bankOperationType)
        .set('sort', 'name')
        .set('order', 'asc'))
        .subscribe(categories => this.categories = categories);
  }

  create(): void {
    let bankOperation = this.bankOperationForm.value as BankOperationRequestDto;

    this._bankOperationApiService.create(bankOperation).subscribe(() => {
      if (this.bankAccountId == ""){
        this._router.navigateByUrl('bankOperations');
      } else {
        // this._router.navigateByUrl('bankOperations?bankAccount=' + this.bankAccountId);
          this._router.navigate(['/bankOperations'],{
            queryParams: {bankAccount: this.bankAccountId, user: this.userId}
          });
      }
    });
  }

  goBack(): void {
    this._location.back();
  }

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['bankAccount'] != undefined) {
        this.bankAccountId = params['bankAccount'];
      }
      if (params['user'] != undefined) {
        this.userId = params['user'];
      }
      this.bankOperationForm.setValue({
        operationType: 'INCOME',
        bankOperationType: '',
        categoryId: '',
        recipientBankAccountId: '',
        amount: '',
        bankAccountId: this.bankAccountId
      })
    })
  }


}
