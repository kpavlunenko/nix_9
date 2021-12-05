import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";

import { CompanyResponseDto } from "../../../../model/company-response-dto";
import { CompanyApiService } from "../../../../service/company-api.service";

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css']
})
export class CompanyDetailsComponent implements OnInit {

  @Input() company?: CompanyResponseDto;

  constructor(private route: ActivatedRoute,
              private _companyApiService: CompanyApiService,
              private location: Location) { }

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this._companyApiService.getCompany(id)
      .subscribe(company => this.company = company);
  }

  goBack(): void {
    this.location.back();
  }
}
