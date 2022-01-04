import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {CurrencyApiService} from "../../../../service/currency-api.service";

@Component({
  selector: 'app-currency-items',
  templateUrl: './currency-items.component.html',
  styleUrls: ['./currency-items.component.css']
})
export class CurrencyItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  currencies: CurrencyResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'code', isActive: false, isSortable: true, sort: 'code', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _currencyApiService: CurrencyApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getCurrencies();
  }

  getCurrencies(): void {
    this._currencyApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._currencyApiService.getCurrencies(this.initHttpParams())
      .subscribe(currencies => this.currencies = currencies);
  }

  deleteById(id: number): void {
    this._currencyApiService.deleteById(id).subscribe(() => {
      this.getCurrencies();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getCurrencies();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getCurrencies();
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

    this.getCurrencies();
  }

  createCurrency(): void {
    this._router.navigateByUrl('currencies/new');
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
