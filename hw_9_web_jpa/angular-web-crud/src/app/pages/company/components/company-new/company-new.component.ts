import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Location } from "@angular/common";

import { FormControl, FormGroup } from "@angular/forms";
import { CompanyApiService } from "../../../../service/company-api.service";
import { CompanyRequestDto } from "../../../../model/company-request-dto";
import { TypeApiService } from "../../../../service/type-api.service";

@Component({
  selector: 'app-company-new',
  templateUrl: './company-new.component.html',
  styleUrls: ['./company-new.component.css']
})
export class CompanyNewComponent implements OnInit {

  company?: CompanyRequestDto;
  companyTypes?: string[];

  companyForm = new FormGroup({
    name: new FormControl(''),
    companyType: new FormControl('')
  });

  constructor(private _companyApiService: CompanyApiService,
              private _typeApiService: TypeApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getCompanyTypes();
  }

  create(): void {
    let company = this.companyForm.value as CompanyRequestDto;

    this._companyApiService.create(company).subscribe(() => {
      this._router.navigateByUrl('companies');
    });
  }

  getCompanyTypes(): void {
    this._typeApiService.getTypes('companyTypes')
      .subscribe(companyTypes => this.companyTypes = companyTypes);
  }

  goBack(): void {
    this.location.back();
  }

}
