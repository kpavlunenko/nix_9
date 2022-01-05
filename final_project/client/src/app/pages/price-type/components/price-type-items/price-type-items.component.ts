import {Component, OnInit} from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {PriceTypeResponseDto} from "../../../../model/price-type/price-type-response-dto";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";
import {appConstRole} from 'src/app/app.const.role';

@Component({
  selector: 'app-price-type-items',
  templateUrl: './price-type-items.component.html',
  styleUrls: ['./price-type-items.component.css']
})
export class PriceTypeItemsComponent implements OnInit {

  appConstRole = appConstRole;
  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  priceTypes: PriceTypeResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _priceTypeApiService: PriceTypeApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getPriceTypes();
  }

  getPriceTypes(): void {
    this._priceTypeApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._priceTypeApiService.getPriceTypes(this.initHttpParams())
      .subscribe(priceTypes => this.priceTypes = priceTypes);
  }

  deleteById(id: number): void {
    this._priceTypeApiService.deleteById(id).subscribe(() => {
      this.getPriceTypes();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getPriceTypes();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getPriceTypes();
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

    this.getPriceTypes();
  }

  createPriceType(): void {
    this._router.navigateByUrl('price_types/new');
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
