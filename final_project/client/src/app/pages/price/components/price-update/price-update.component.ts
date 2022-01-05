import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";
import {formatDate, Location} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {PriceTypeResponseDto} from "../../../../model/price-type/price-type-response-dto";
import {PriceRequestDto} from "../../../../model/prices/price-request-dto";
import {NomenclatureResponseDto} from "../../../../model/nomenclature/nomenclature-response-dto";
import {PriceResponseDto} from "../../../../model/prices/price-response-dto";
import {PriceApiService} from "../../../../service/price-api.service";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";

@Component({
  selector: 'app-price-update',
  templateUrl: './price-update.component.html',
  styleUrls: ['./price-update.component.css']
})
export class PriceUpdateComponent implements OnInit {

  id: number = 0;
  price?: PriceRequestDto;
  priceTypes?: PriceTypeResponseDto[];
  nomenclatures?: NomenclatureResponseDto[];
  @Input() priceResponseDto?: Observable<PriceResponseDto>;

  priceForm = new FormGroup({
    date: new FormControl(formatDate(new Date(), 'yyyy-MM-dd','en-US')),
    priceTypeId: new FormControl(''),
    nomenclatureId: new FormControl(''),
    price: new FormControl('')
  });

  constructor(private _priceApiService: PriceApiService,
              private _priceTypeApiService: PriceTypeApiService,
              private _nomenclatureApiService: NomenclatureApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getPrice();
    this.getPriceTypes();
    this.getNomenclatures();
  }

  getPriceTypes(): void {
    this._priceTypeApiService.getPriceTypes(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(priceTypes => this.priceTypes = priceTypes);
  }

  getNomenclatures(): void {
    this._nomenclatureApiService.getNomenclatures(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(nomenclatures => this.nomenclatures = nomenclatures);
  }

  update(): void {
    let price = this.priceForm.value as PriceRequestDto;
    this._priceApiService.update(this.id, price).subscribe(() => {
      this._router.navigateByUrl('prices');
    });
  }

  getPrice(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.priceResponseDto = this._priceApiService.getPrice(this.id);
    this.priceResponseDto.subscribe(priceResponseDto =>
      this.priceForm.setValue({
        date: formatDate(priceResponseDto.date, 'yyyy-MM-dd','en-US'),
        priceTypeId: priceResponseDto.priceType.id,
        nomenclatureId: priceResponseDto.nomenclature.id,
        price: priceResponseDto.price
      }))
  }

  goBack(): void {
    this._location.back();
  }
}
