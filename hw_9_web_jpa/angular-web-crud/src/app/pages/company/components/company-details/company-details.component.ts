import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";
import {Router} from "@angular/router";

import { CompanyResponseDto } from "../../../../model/company-response-dto";
import { CompanyApiService } from "../../../../service/company-api.service";

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css']
})
export class CompanyDetailsComponent implements OnInit {

  id?:number;

  @Input() company?: CompanyResponseDto;

  constructor(private route: ActivatedRoute,
              private _companyApiService: CompanyApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._companyApiService.getCompany(this.id)
      .subscribe(company => this.company = company);
  }

  goBack(): void {
    this.location.back();
  }

  goToAgreements(): void {
    this._router.navigate(['/agreements'],{
      queryParams: { companyId: this.id}
    });
  }

  updateCompany(): void {
    this._router.navigateByUrl('companies/update/' + this.id);
  }
}
