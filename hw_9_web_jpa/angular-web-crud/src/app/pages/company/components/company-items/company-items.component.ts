import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {CompanyResponseDto} from '../../../../model/company-response-dto';
import {CompanyApiService} from "../../../../service/company-api.service";
import {TableHeader} from "../../../../model/table-header";

@Component({
  selector: 'app-companies',
  templateUrl: './company-items.component.html',
  styleUrls: ['./company-items.component.css']
})
export class CompanyItemsComponent implements OnInit {

  companies: CompanyResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
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
    this._companyApiService.getCompanies()
      .subscribe(companies => this.companies = companies);
  }

  deleteById(id: number): void {
    this._companyApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
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

    this._companyApiService.getCompaniesWithParams(sort, order)
      .subscribe(companies => this.companies = companies);
  }

  createCompany(): void {
    this._router.navigateByUrl('companies/new');
  }

}
