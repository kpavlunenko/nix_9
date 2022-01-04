import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {formatDate, Location} from "@angular/common";
import {CurrencyRateRequestDto} from "../../../../model/currency-rate/currency-rate-request-dto";
import {CurrencyRateApiService} from "../../../../service/currency-rate-api.service";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {HttpParams} from "@angular/common/http";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";

@Component({
  selector: 'app-currency-rate-new',
  templateUrl: './currency-rate-new.component.html',
  styleUrls: ['./currency-rate-new.component.css']
})
export class CurrencyRateNewComponent implements OnInit {

  currencyRate?: CurrencyRateRequestDto;
  currencies?: CurrencyResponseDto[];

  currencyRateForm = new FormGroup({
    date: new FormControl(formatDate(new Date(), 'yyyy-MM-dd','en-US')),
    currencyId: new FormControl(''),
    rate: new FormControl(''),
    frequencyRate: new FormControl('')
  });


  constructor(private _currencyRateApiService: CurrencyRateApiService,
              private _currencyApiService: CurrencyApiService,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.getCurrencies();
  }

  getCurrencies(): void {
    this._currencyApiService.getCurrencies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(currencies => this.currencies = currencies);
  }

  create(): void {
    let currencyRate = this.currencyRateForm.value as CurrencyRateRequestDto;

    this._currencyRateApiService.create(currencyRate).subscribe(() => {
      this._router.navigateByUrl('currency_rates');
    });
  }

  goBack(): void {
    this._location.back();
  }

}
