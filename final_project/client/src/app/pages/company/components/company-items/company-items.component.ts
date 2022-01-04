import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {CompanyResponseDto} from '../../../../model/company/company-response-dto';
import {CompanyApiService} from "../../../../service/company-api.service";
import {TableHeader} from "../../../../model/table-header";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-companies',
  templateUrl: './company-items.component.html',
  styleUrls: ['./company-items.component.css']
})
export class CompanyItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  companies: CompanyResponseDto[] = [];
  headers: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'company type', isActive: false, isSortable: true, sort: 'companyType', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _companyApiService: CompanyApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getCompanies();
  }

  getCompanies(): void {
    this._companyApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._companyApiService.getCompanies(this.initHttpParams())
      .subscribe(companies => this.companies = companies);
  }

  deleteById(id: number): void {
    this._companyApiService.deleteById(id).subscribe(() => {
      this.getCompanies();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getCompanies();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getCompanies();
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

    this.getCompanies();
  }

  createCompany(): void {
    this._router.navigateByUrl('companies/new');
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
