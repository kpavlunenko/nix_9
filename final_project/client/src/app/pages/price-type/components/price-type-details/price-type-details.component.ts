import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {PriceTypeResponseDto} from "../../../../model/price-type/price-type-response-dto";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";

@Component({
  selector: 'app-price-type-details',
  templateUrl: './price-type-details.component.html',
  styleUrls: ['./price-type-details.component.css']
})
export class PriceTypeDetailsComponent implements OnInit {

  id?:number;

  @Input() priceType?: PriceTypeResponseDto;

  constructor(private _route: ActivatedRoute,
              private _priceTypeApiService: PriceTypeApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getPriceType();
  }

  getPriceType(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._priceTypeApiService.getPriceType(this.id)
      .subscribe(priceType => this.priceType = priceType);
  }

  goBack(): void {
    this._location.back();
  }

  updatePriceType(): void {
    this._router.navigateByUrl('price_types/update/' + this.id);
  }

}
