import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {SalesInvoiceResponseDto} from "../../../../model/sales-invoice/sales-invoice-response-dto";
import {SalesInvoiceApiService} from "../../../../service/sales-invoice-api.service";

@Component({
  selector: 'app-sales-invoice-items',
  templateUrl: './sales-invoice-items.component.html',
  styleUrls: ['./sales-invoice-items.component.css']
})
export class SalesInvoiceItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  salesInvoices: SalesInvoiceResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'date', isActive: false, isSortable: true, sort: 'date', order: 'asc'},
    {headerName: 'company', isActive: false, isSortable: true, sort: 'company', order: 'asc'},
    {headerName: 'counterparty', isActive: false, isSortable: true, sort: 'counterparty', order: 'asc'},
    {headerName: 'currency', isActive: false, isSortable: true, sort: 'currency', order: 'asc'},
    {headerName: 'priceType', isActive: false, isSortable: true, sort: 'priceType', order: 'asc'},
    {headerName: 'sum', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _salesInvoiceApiService: SalesInvoiceApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getSalesInvoices();
  }

  getSalesInvoices(): void {
    this._salesInvoiceApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._salesInvoiceApiService.getSalesInvoices(this.initHttpParams())
      .subscribe(salesInvoices => this.salesInvoices = salesInvoices);
  }

  deleteById(id: number): void {
    this._salesInvoiceApiService.deleteById(id).subscribe(() => {
      this.getSalesInvoices();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getSalesInvoices();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getSalesInvoices();
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

    this.getSalesInvoices();
  }

  createSalesInvoice(): void {
    this._router.navigateByUrl('sales_invoices/new');
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
