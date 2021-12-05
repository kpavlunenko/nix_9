import { Component, OnInit } from '@angular/core';
import { CompanyResponseDto } from '../model/company-response-dto';
import { CompanyApiService } from '../service/company-api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  companies: CompanyResponseDto[] = [];

  constructor(private companyService: CompanyApiService) { }

  ngOnInit() {
    this.getCompanies();
  }

  getCompanies(): void {
    this.companyService.getCompanies()
      .subscribe(companies => this.companies = companies.slice(1, 5));
  }
}
