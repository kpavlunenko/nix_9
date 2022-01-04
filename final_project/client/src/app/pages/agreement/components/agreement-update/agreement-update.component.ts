import {Component, Input, OnInit} from '@angular/core';
import {AgreementRequestDto} from "../../../../model/agreement/agreement-request-dto";
import {CompanyResponseDto} from "../../../../model/company/company-response-dto";
import {CounterpartyResponseDto} from "../../../../model/counterparty/counterparty-response-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {AgreementApiService} from "../../../../service/agreement-api.service";
import {TypeApiService} from "../../../../service/type-api.service";
import {CompanyApiService} from "../../../../service/company-api.service";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {AgreementResponseDto} from "../../../../model/agreement/agreement-response-dto";
import {Observable} from "rxjs";

@Component({
  selector: 'app-agreement-update',
  templateUrl: './agreement-update.component.html',
  styleUrls: ['./agreement-update.component.css']
})
export class AgreementUpdateComponent implements OnInit {

  id: number = 0;
  agreement?: AgreementRequestDto;
  @Input() agreementResponseDto?: Observable<AgreementResponseDto>;
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
              private location: Location) {
  }

  ngOnInit(): void {
    this.getAgreementTypes();
    this.getAgreement();
    this.getCompanies();
    this.getCounterparties();
  }

  update(): void {
    let agreement = this.agreementForm.value as AgreementRequestDto;
    this._agreementApiService.update(this.id, agreement).subscribe(() => {
      this._router.navigateByUrl('agreements');
    });
  }

  getAgreement(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.agreementResponseDto = this._agreementApiService.getAgreement(this.id);
    this.agreementResponseDto.subscribe(agreementResponseDto =>
      this.agreementForm.setValue({
        name: agreementResponseDto.name,
        agreementType: agreementResponseDto.agreementType,
        companyId: agreementResponseDto.company.id,
        counterpartyId: agreementResponseDto.counterparty.id
      }))
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
