import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {BankAccountResponseDto} from "../../../../model/bank-account-response-dto";
import {BankAccountApiService} from "../../../../service/bank-account-api.service";

@Component({
  selector: 'app-bank-account-items',
  templateUrl: './bank-account-items.component.html',
  styleUrls: ['./bank-account-items.component.css']
})
export class BankAccountItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  bankAccounts: BankAccountResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'iban', isActive: false, isSortable: true, sort: 'iban', order: 'asc'},
    {headerName: 'user', isActive: false, isSortable: true, sort: 'user.firstName', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _bankAccountApiService: BankAccountApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getBankAccounts();
  }

  getBankAccounts(): void {
    this._bankAccountApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
          if (this.totalPageSize == 0) {
            this.totalPageSize = 1;
          }
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._bankAccountApiService.getBankAccounts(this.initHttpParams())
      .subscribe(bankAccounts => this.bankAccounts = bankAccounts);
  }

  deleteById(id: number): void {
    this._bankAccountApiService.deleteById(id).subscribe(() => {
      this.getBankAccounts();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getBankAccounts();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getBankAccounts();
  }

  sortOn(sort: string, order: string) {
    // @ts-ignore
    this.headers.forEach(function (header) {
      if (header.sort == sort) {
        header.isActive = true;
        header.order = order;
      } else {
        header.isActive = false;
      }
    })

    this.sort = sort;
    this.order = order;

    this.getBankAccounts();
  }

  createBankAccount(): void {
    this._router.navigateByUrl('bankAccounts/new');
  }

  initHttpParams(): any {
    return new HttpParams()
      .set('sort', this.sort)
      .set('order', this.order)
      .set('currentPage', this.currentPage - 1)
      .set('sizeOfPage', this.sizeOfPage)
      ;
  }

}
