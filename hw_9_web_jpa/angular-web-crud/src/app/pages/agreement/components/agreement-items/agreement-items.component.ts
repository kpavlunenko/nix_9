import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {AgreementApiService} from "../../../../service/agreement-api.service";
import {HttpParams} from "@angular/common/http";
import {AgreementResponseDto} from "../../../../model/agreement-response-dto";

@Component({
  selector: 'app-agreement-items',
  templateUrl: './agreement-items.component.html',
  styleUrls: ['./agreement-items.component.css']
})
export class AgreementItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  agreements: AgreementResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'agreement type', isActive: false, isSortable: true, sort: 'agreementType', order: 'asc'},
    {headerName: 'company', isActive: false, isSortable: false, sort: 'company', order: 'asc'},
    {headerName: 'counterparty', isActive: false, isSortable: false, sort: 'counterparty', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _agreementApiService: AgreementApiService,
              private _router: Router) { }

  ngOnInit(): void {
    this.getAgreements();
  }

  getAgreements(): void {
    this._agreementApiService.count()
      .subscribe(countOfItems => this.countOfItems = countOfItems);

    if (this.countOfItems % this.sizeOfPage == 0) {
      this.totalPageSize = this.countOfItems / this.sizeOfPage;
    } else {
      this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
    }

    this._agreementApiService.getAgreements(this.initHttpParams())
      .subscribe(agreements => this.agreements = agreements);
  }

  deleteById(id: number): void {
    this._agreementApiService.deleteById(id).subscribe(() => {
      this.getAgreements();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getAgreements();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getAgreements();
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

    this.getAgreements();
  }

  createAgreement(): void {
    this._router.navigateByUrl('agreements/new');
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
