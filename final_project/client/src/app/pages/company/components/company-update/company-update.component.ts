import {Component, Input, OnInit} from '@angular/core';
import {CompanyRequestDto} from "../../../../model/company/company-request-dto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {CompanyApiService} from "../../../../service/company-api.service";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CompanyResponseDto} from "../../../../model/company/company-response-dto";
import {Observable} from "rxjs";
import {BusinessDirectionResponseDto} from "../../../../model/business-direction/business-direction-response-dto";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-company-update',
  templateUrl: './company-update.component.html',
  styleUrls: ['./company-update.component.css']
})
export class CompanyUpdateComponent implements OnInit {

  id: number = 0;
  company?: CompanyRequestDto;
  companyTypes?: string[];
  businessDirections?: BusinessDirectionResponseDto[];
  @Input() companyResponseDto?: Observable<CompanyResponseDto>;

  companyForm = new FormGroup({
    name: new FormControl(''),
    companyType: new FormControl(''),
    businessDirectionIds: new FormArray([])
  });

  constructor(private _companyApiService: CompanyApiService,
              private _businessDirectionApiService: BusinessDirectionApiService,
              private _typeApiService: TypeApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCompanyTypes();
    this.getCompany();
    this.getBusinessDirections();
  }

  getFormsControls() : FormArray{
    return this.companyForm.controls['businessDirectionIds'] as FormArray;
  }

  addBusinessDirection(value?:number){
    (<FormArray>this.companyForm.controls["businessDirectionIds"]).push(new FormControl(value));
  }
  removeBusinessDirection(index: number){
    (<FormArray>this.companyForm.controls["businessDirectionIds"]).removeAt(index);
  }

  getBusinessDirections(): void {
    this._businessDirectionApiService.getBusinessDirections(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(businessDirections => this.businessDirections = businessDirections);
  }

  update(): void {
    let company = this.companyForm.value as CompanyRequestDto;

    this._companyApiService.update(this.id, company).subscribe(() => {
      this._router.navigateByUrl('companies');
    });
  }

  getCompany(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.companyResponseDto = this._companyApiService.getCompany(this.id);
    this.companyResponseDto.subscribe(companyResponseDto =>
      this.companyForm.setValue({name: companyResponseDto.name, companyType: companyResponseDto.companyType, businessDirectionIds: []}))
    this.companyResponseDto.subscribe(companyResponseDto => companyResponseDto.businessDirections.forEach(value => {this.addBusinessDirection(value.id)}))
  }

  getCompanyTypes(): void {
    this._typeApiService.getTypes('companyTypes')
      .subscribe(companyTypes => this.companyTypes = companyTypes);
  }

  goBack(): void {
    this.location.back();
  }

}
