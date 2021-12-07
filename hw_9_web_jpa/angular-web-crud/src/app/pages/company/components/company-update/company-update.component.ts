import {Component, Input, OnInit} from '@angular/core';
import {CompanyRequestDto} from "../../../../model/company-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {CompanyApiService} from "../../../../service/company-api.service";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CompanyResponseDto} from "../../../../model/company-response-dto";
import {Observable} from "rxjs";

@Component({
  selector: 'app-company-update',
  templateUrl: './company-update.component.html',
  styleUrls: ['./company-update.component.css']
})
export class CompanyUpdateComponent implements OnInit {

  id: number = 0;
  company?: CompanyRequestDto;
  companyTypes?: string[];
  @Input() companyResponseDto?: Observable<CompanyResponseDto>;

  companyForm = new FormGroup({
    name: new FormControl(''),
    companyType: new FormControl('')
  });

  constructor(private _companyApiService: CompanyApiService,
              private _typeApiService: TypeApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCompanyTypes();
    this.getCompany();
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
      this.companyForm.setValue({name: companyResponseDto.name, companyType: companyResponseDto.companyType}))
  }

  getCompanyTypes(): void {
    this._typeApiService.getTypes('companyTypes')
      .subscribe(companyTypes => this.companyTypes = companyTypes);
  }

  goBack(): void {
    this.location.back();
  }

}
