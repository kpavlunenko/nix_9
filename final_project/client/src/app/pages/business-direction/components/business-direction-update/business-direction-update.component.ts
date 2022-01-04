import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BusinessDirectionResponseDto} from "../../../../model/business-direction/business-direction-response-dto";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {BusinessDirectionRequestDto} from "../../../../model/business-direction/business-direction-request-dto";
import {CompanyResponseDto} from "../../../../model/company/company-response-dto";
import {CompanyApiService} from "../../../../service/company-api.service";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-business-direction-update',
  templateUrl: './business-direction-update.component.html',
  styleUrls: ['./business-direction-update.component.css']
})
export class BusinessDirectionUpdateComponent implements OnInit {

  id: number = 0;
  businessDirection?: BusinessDirectionRequestDto;
  companies?: CompanyResponseDto[];
  @Input() businessDirectionResponseDto?: Observable<BusinessDirectionResponseDto>;

  businessDirectionForm = new FormGroup({
    name: new FormControl(''),
    companyIds: new FormArray([])
  });

  constructor(private _businessDirectionApiService: BusinessDirectionApiService,
              private _companyApiService: CompanyApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getBusinessDirection();
    this.getCompanies();
  }

  getFormsControls() : FormArray{
    return this.businessDirectionForm.controls['companyIds'] as FormArray;
  }

  addCompany(value?:number){
    (<FormArray>this.businessDirectionForm.controls["companyIds"]).push(new FormControl(value));
  }
  removeCompany(index: number){
    (<FormArray>this.businessDirectionForm.controls["companyIds"]).removeAt(index);
  }

  getCompanies(): void {
    this._companyApiService.getCompanies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(companies => this.companies = companies);
  }

  update(): void {
    let businessDirection = this.businessDirectionForm.value as BusinessDirectionRequestDto;

    this._businessDirectionApiService.update(this.id, businessDirection).subscribe(() => {
      this._router.navigateByUrl('business_directions');
    });
  }

  getBusinessDirection(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.businessDirectionResponseDto = this._businessDirectionApiService.getBusinessDirection(this.id);

    this.businessDirectionResponseDto.subscribe(businessDirectionResponseDto =>
      this.businessDirectionForm.setValue({name: businessDirectionResponseDto.name,
        companyIds: []}))
    this.businessDirectionResponseDto.subscribe(businessDirectionResponseDto => businessDirectionResponseDto.companies.forEach(value => {this.addCompany(value.id)}))
  }

  goBack(): void {
    this.location.back();
  }

}
