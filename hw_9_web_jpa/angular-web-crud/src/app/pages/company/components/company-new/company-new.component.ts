import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Location } from "@angular/common";

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import { CompanyApiService } from "../../../../service/company-api.service";
import { CompanyRequestDto } from "../../../../model/company-request-dto";
import { TypeApiService } from "../../../../service/type-api.service";
import {BusinessDirectionResponseDto} from "../../../../model/business-direction-response-dto";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-company-new',
  templateUrl: './company-new.component.html',
  styleUrls: ['./company-new.component.css']
})
export class CompanyNewComponent implements OnInit {

  company?: CompanyRequestDto;
  companyTypes?: string[];
  businessDirections?: BusinessDirectionResponseDto[];

  companyForm = new FormGroup({
    name: new FormControl(''),
    companyType: new FormControl(''),
    businessDirectionIds: new FormArray([
      new FormControl('')])
  });

  constructor(private _companyApiService: CompanyApiService,
              private _businessDirectionApiService: BusinessDirectionApiService,
              private _typeApiService: TypeApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getCompanyTypes();
    this.getBusinessDirections();
  }

  getFormsControls(): FormArray {
    return this.companyForm.controls['businessDirectionIds'] as FormArray;
  }

  addBusinessDirection() {
    (<FormArray>this.companyForm.controls["businessDirectionIds"]).push(new FormControl(''));
  }

  removeBusinessDirection(index: number) {
    (<FormArray>this.companyForm.controls["businessDirectionIds"]).removeAt(index);
  }

  getBusinessDirections(): void {
    this._businessDirectionApiService.getBusinessDirections(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(businessDirections => this.businessDirections = businessDirections);
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
