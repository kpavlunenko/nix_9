import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {PriceResponseDto} from "../../../../model/prices/price-response-dto";
import {PriceApiService} from "../../../../service/price-api.service";

@Component({
  selector: 'app-price-items',
  templateUrl: './price-items.component.html',
  styleUrls: ['./price-items.component.css']
})
export class PriceItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "date";
  order: string = "desc";
  prices: PriceResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'date', isActive: false, isSortable: true, sort: 'date', order: 'asc'},
    {headerName: 'price type', isActive: false, isSortable: true, sort: 'priceType', order: 'asc'},
    {headerName: 'nomenclature', isActive: false, isSortable: true, sort: 'nomenclature', order: 'asc'},
    {headerName: 'price', isActive: false, isSortable: true, sort: 'price', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _priceApiService: PriceApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getPrices();
  }

  getPrices(): void {
    this._priceApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._priceApiService.getPrices(this.initHttpParams())
      .subscribe(prices => this.prices = prices);
  }

  deleteById(id: number): void {
    this._priceApiService.deleteById(id).subscribe(() => {
      this.getPrices();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getPrices();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getPrices();
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

    this.getPrices();
  }

  createPrice(): void {
    this._router.navigateByUrl('prices/new');
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
