import { Component, OnInit } from '@angular/core';
import {AgreementRequestDto} from "../../../../model/agreement-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {AgreementApiService} from "../../../../service/agreement-api.service";
import {CompanyApiService} from "../../../../service/company-api.service";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {CompanyResponseDto} from "../../../../model/company-response-dto";
import {CounterpartyResponseDto} from "../../../../model/counterparty-response-dto";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-agreement-new',
  templateUrl: './agreement-new.component.html',
  styleUrls: ['./agreement-new.component.css']
})
export class AgreementNewComponent implements OnInit {

  agreement?: AgreementRequestDto;
  agreementTypes?: string[];
  companies?: CompanyResponseDto[];
  counterparties?: CounterpartyResponseDto[];

  agreementForm = new FormGroup({
    name: new FormControl(''),
    agreementType: new FormControl(''),
    companyId: new FormControl(''),
    counterpartyId: new FormControl('')
  });

  constructor(private _agreementApiService: AgreementApiService,
              private _typeApiService: TypeApiService,
              private _companyApiService: CompanyApiService,
              private _counterpartyApiService: CounterpartyApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) { }

  ngOnInit(): void {
    this.getAgreementTypes();
    this.getCompanies();
    this.getCounterparties();
  }

  create(): void {
    let agreement = this.agreementForm.value as AgreementRequestDto;
    this._agreementApiService.create(agreement).subscribe(() => {
      this._router.navigateByUrl('agreements');
    });
  }

  getAgreementTypes(): void {
    this._typeApiService.getTypes('agreementTypes')
      .subscribe(agreementTypes => this.agreementTypes = agreementTypes);
  }

  getCompanies(): void {
    this._companyApiService.getCompanies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(companies => this.companies = companies);
  }

  getCounterparties(): void {
    this._counterpartyApiService.getCounterparties(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(counterparties => this.counterparties = counterparties);
  }

  goBack(): void {
    this.location.back();
  }

}
