import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { CompanyResponseDto } from '../../../../model/company-response-dto';
import { CompanyApiService} from "../../../../service/company-api.service";

@Component({
  selector: 'app-companies',
  templateUrl: './company-items.component.html',
  styleUrls: ['./company-items.component.css']
})
export class CompanyItemsComponent implements OnInit {

  companies: CompanyResponseDto[] = [];

  constructor(private _companyApiService: CompanyApiService,
              private _router: Router) { }

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

  createCompany(): void {
    this._router.navigateByUrl('companies/new');
  }

}
