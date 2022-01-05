import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {formatDate, Location} from "@angular/common";
import {CurrencyRateRequestDto} from "../../../../model/currency-rate/currency-rate-request-dto";
import {CurrencyRateResponseDto} from "../../../../model/currency-rate/currency-rate-response-dto";
import {CurrencyRateApiService} from "../../../../service/currency-rate-api.service";
import {HttpParams} from "@angular/common/http";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";

@Component({
  selector: 'app-currency-rate-update',
  templateUrl: './currency-rate-update.component.html',
  styleUrls: ['./currency-rate-update.component.css']
})
export class CurrencyRateUpdateComponent implements OnInit {

  id: number = 0;
  currencyRate?: CurrencyRateRequestDto;
  currencies?: CurrencyResponseDto[];
  @Input() currencyRateResponseDto?: Observable<CurrencyRateResponseDto>;

  currencyRateForm = new FormGroup({
    date: new FormControl(formatDate(new Date(), 'yyyy-MM-dd','en-US')),
    currencyId: new FormControl(''),
    rate: new FormControl(''),
    frequencyRate: new FormControl('')
  });

  constructor(private _currencyRateApiService: CurrencyRateApiService,
              private _currencyApiService: CurrencyApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCurrencyRate();
    this.getCurrencies();
  }

  getCurrencies(): void {
    this._currencyApiService.getCurrencies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(currencies => this.currencies = currencies);
  }

  update(): void {
    let currencyRate = this.currencyRateForm.value as CurrencyRateRequestDto;
    this._currencyRateApiService.update(this.id, currencyRate).subscribe(() => {
      this._router.navigateByUrl('currency_rates');
    });
  }

  getCurrencyRate(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.currencyRateResponseDto = this._currencyRateApiService.getCurrencyRate(this.id);
    this.currencyRateResponseDto.subscribe(currencyRateResponseDto =>
      this.currencyRateForm.setValue({
        date: formatDate(currencyRateResponseDto.date, 'yyyy-MM-dd','en-US'),
        currencyId: currencyRateResponseDto.currency.id,
        rate: currencyRateResponseDto.rate,
        frequencyRate: currencyRateResponseDto.frequencyRate
      }))
  }

  goBack(): void {
    this._location.back();
  }

}
