import { Component, OnInit } from '@angular/core';
import {BusinessDirectionResponseDto} from "../../../../model/business-direction-response-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {NomenclatureRequestDto} from "../../../../model/nomenclature-request-dto";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";

@Component({
  selector: 'app-nomenclature-new',
  templateUrl: './nomenclature-new.component.html',
  styleUrls: ['./nomenclature-new.component.css']
})
export class NomenclatureNewComponent implements OnInit {

  nomenclature?: NomenclatureRequestDto;
  businessDirections?: BusinessDirectionResponseDto[];
  isProduct: string = 'true';

  nomenclatureForm = new FormGroup({
    name: new FormControl('', Validators.required),
    businessDirectionId: new FormControl(''),
    isProduct: new FormControl('')
  });

  constructor(private _nomenclatureApiService: NomenclatureApiService,
              private _businessDirectionApiService: BusinessDirectionApiService,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.getBusinessDirections();
  }

  getBusinessDirections(): void {
    this._businessDirectionApiService.getBusinessDirections(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(businessDirections => this.businessDirections = businessDirections);
  }

  create(): void {
    let nomenclature = this.nomenclatureForm.value as NomenclatureRequestDto;
    if (this.isProduct == 'true') {
      nomenclature.service = false;
      nomenclature.product = true;
    } else {
      nomenclature.service = true;
      nomenclature.product = false;
    }
    this._nomenclatureApiService.create(nomenclature).subscribe(() => {
      this._router.navigateByUrl('nomenclatures');
    });
  }

  goBack(): void {
    this._location.back();
  }

}
