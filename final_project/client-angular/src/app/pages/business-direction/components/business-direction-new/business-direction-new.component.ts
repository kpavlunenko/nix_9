import {Component, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BusinessDirectionRequestDto} from "../../../../model/business-direction-request-dto";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {CompanyApiService} from "../../../../service/company-api.service";
import {CompanyResponseDto} from "../../../../model/company-response-dto";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-business-direction-new',
  templateUrl: './business-direction-new.component.html',
  styleUrls: ['./business-direction-new.component.css']
})
export class BusinessDirectionNewComponent implements OnInit {

  businessDirection?: BusinessDirectionRequestDto;
  companies?: CompanyResponseDto[];

  businessDirectionForm = new FormGroup({
    name: new FormControl(''),
    companyIds: new FormArray([
      new FormControl('')])
  });


  constructor(private _businessDirectionApiService: BusinessDirectionApiService,
              private _companyApiService: CompanyApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getCompanies();
  }

  getFormsControls(): FormArray {
    return this.businessDirectionForm.controls['companyIds'] as FormArray;
  }

  addCompany() {
    (<FormArray>this.businessDirectionForm.controls["companyIds"]).push(new FormControl(''));
  }

  removeCompany(index: number) {
    (<FormArray>this.businessDirectionForm.controls["companyIds"]).removeAt(index);
  }

  getCompanies(): void {
    this._companyApiService.getCompanies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(companies => this.companies = companies);
  }

  create(): void {
    let businessDirection = this.businessDirectionForm.value as BusinessDirectionRequestDto;

    this._businessDirectionApiService.create(businessDirection).subscribe(() => {
      this._router.navigateByUrl('business_directions');
    });
  }

  goBack(): void {
    this.location.back();
  }

}
