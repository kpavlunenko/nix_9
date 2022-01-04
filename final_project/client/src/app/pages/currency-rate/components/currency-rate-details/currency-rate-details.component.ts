import {Component, Input, OnInit} from '@angular/core';
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {Location} from "@angular/common";
import {CurrencyRateApiService} from "../../../../service/currency-rate-api.service";
import {CurrencyRateResponseDto} from "../../../../model/currency-rate/currency-rate-response-dto";

@Component({
  selector: 'app-currency-rate-details',
  templateUrl: './currency-rate-details.component.html',
  styleUrls: ['./currency-rate-details.component.css']
})
export class CurrencyRateDetailsComponent implements OnInit {

  id?:number;

  @Input() currencyRate?: CurrencyRateResponseDto;

  constructor(private _route: ActivatedRoute,
              private _currencyRateApiService: CurrencyRateApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getCurrency();
  }

  getCurrency(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._currencyRateApiService.getCurrencyRate(this.id)
      .subscribe(currencyRate => this.currencyRate = currencyRate);
  }

  goBack(): void {
    this._location.back();
  }

  updateCurrencyRate(): void {
    this._router.navigateByUrl('currency_rates/update/' + this.id);
  }

}
