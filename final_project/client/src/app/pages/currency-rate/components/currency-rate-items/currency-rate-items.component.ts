import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {CurrencyRateResponseDto} from "../../../../model/currency-rate/currency-rate-response-dto";
import {CurrencyRateApiService} from "../../../../service/currency-rate-api.service";

@Component({
  selector: 'app-currency-rate-items',
  templateUrl: './currency-rate-items.component.html',
  styleUrls: ['./currency-rate-items.component.css']
})
export class CurrencyRateItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "date";
  order: string = "desc";
  currencyRates: CurrencyRateResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'date', isActive: false, isSortable: true, sort: 'date', order: 'asc'},
    {headerName: 'currency', isActive: false, isSortable: true, sort: 'currency', order: 'asc'},
    {headerName: 'rate', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'frequency rate', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _currencyRateApiService: CurrencyRateApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getCurrencyRates();
  }

  getCurrencyRates(): void {
    this._currencyRateApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._currencyRateApiService.getCurrencyRates(this.initHttpParams())
      .subscribe(currencyRates => this.currencyRates = currencyRates);
  }

  deleteById(id: number): void {
    this._currencyRateApiService.deleteById(id).subscribe(() => {
      this.getCurrencyRates();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getCurrencyRates();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getCurrencyRates();
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

    this.getCurrencyRates();
  }

  createCurrencyRate(): void {
    this._router.navigateByUrl('currency_rates/new');
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
