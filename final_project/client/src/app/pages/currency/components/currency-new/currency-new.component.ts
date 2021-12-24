import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {CurrencyRequestDto} from "../../../../model/currency-request-dto";
import {CurrencyApiService} from "../../../../service/currency-api.service";

@Component({
  selector: 'app-currency-new',
  templateUrl: './currency-new.component.html',
  styleUrls: ['./currency-new.component.css']
})
export class CurrencyNewComponent implements OnInit {

  currency?: CurrencyRequestDto;

  currencyForm = new FormGroup({
    name: new FormControl(''),
    code: new FormControl('')
  });


  constructor(private _currencyApiService: CurrencyApiService,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
  }

  create(): void {
    let currency = this.currencyForm.value as CurrencyRequestDto;

    this._currencyApiService.create(currency).subscribe(() => {
      this._router.navigateByUrl('currencies');
    });
  }

  goBack(): void {
    this._location.back();
  }
}
