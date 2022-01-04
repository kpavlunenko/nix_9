import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {CurrencyApiService} from "../../../../service/currency-api.service";

@Component({
  selector: 'app-currency-details',
  templateUrl: './currency-details.component.html',
  styleUrls: ['./currency-details.component.css']
})
export class CurrencyDetailsComponent implements OnInit {

  id?:number;

  @Input() currency?: CurrencyResponseDto;

  constructor(private _route: ActivatedRoute,
              private _currencyApiService: CurrencyApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getCurrency();
  }

  getCurrency(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._currencyApiService.getCurrency(this.id)
      .subscribe(currency => this.currency = currency);
  }

  goBack(): void {
    this._location.back();
  }

  updateCurrency(): void {
    this._router.navigateByUrl('currencies/update/' + this.id);
  }

}
