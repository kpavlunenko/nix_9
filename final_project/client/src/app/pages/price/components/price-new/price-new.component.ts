import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {formatDate, Location} from "@angular/common";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {PriceRequestDto} from "../../../../model/prices/price-request-dto";
import {NomenclatureResponseDto} from "../../../../model/nomenclature/nomenclature-response-dto";
import {PriceTypeResponseDto} from "../../../../model/price-type/price-type-response-dto";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";
import {PriceApiService} from "../../../../service/price-api.service";

@Component({
  selector: 'app-price-new',
  templateUrl: './price-new.component.html',
  styleUrls: ['./price-new.component.css']
})
export class PriceNewComponent implements OnInit {

  price?: PriceRequestDto;
  priceTypes?: PriceTypeResponseDto[];
  nomenclatures?: NomenclatureResponseDto[];

  priceForm = new FormGroup({
    date: new FormControl(formatDate(new Date(), 'yyyy-MM-dd', 'en-US')),
    priceTypeId: new FormControl(''),
    nomenclatureId: new FormControl(''),
    price: new FormControl('')
  });


  constructor(private _priceApiService: PriceApiService,
              private _priceTypeApiService: PriceTypeApiService,
              private _nomenclatureApiService: NomenclatureApiService,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.getNomenclatures();
    this.getPriceTypes();
  }

  getNomenclatures(): void {
    this._nomenclatureApiService.getNomenclatures(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(nomenclatures => this.nomenclatures = nomenclatures);
  }

  getPriceTypes(): void {
    this._priceTypeApiService.getPriceTypes(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(priceTypes => this.priceTypes = priceTypes);
  }

  create(): void {
    let price = this.priceForm.value as PriceRequestDto;

    this._priceApiService.create(price).subscribe(() => {
      this._router.navigateByUrl('prices');
    });
  }

  goBack(): void {
    this._location.back();
  }
}
