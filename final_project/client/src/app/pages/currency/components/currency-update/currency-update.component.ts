import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CurrencyRequestDto} from "../../../../model/currency-request-dto";
import {CurrencyResponseDto} from "../../../../model/currency-response-dto";
import {CurrencyApiService} from "../../../../service/currency-api.service";

@Component({
  selector: 'app-currency-update',
  templateUrl: './currency-update.component.html',
  styleUrls: ['./currency-update.component.css']
})
export class CurrencyUpdateComponent implements OnInit {

  id: number = 0;
  currency?: CurrencyRequestDto;
  @Input() currencyResponseDto?: Observable<CurrencyResponseDto>;

  currencyForm = new FormGroup({
    name: new FormControl('', Validators.required),
    code: new FormControl('', Validators.required)
  });

  constructor(private _currencyApiService: CurrencyApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCurrency();
  }

  update(): void {
    let currency = this.currencyForm.value as CurrencyRequestDto;
    this._currencyApiService.update(this.id, currency).subscribe(() => {
      this._router.navigateByUrl('currencies');
    });
  }

  getCurrency(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.currencyResponseDto = this._currencyApiService.getCurrency(this.id);
    this.currencyResponseDto.subscribe(currencyResponseDto =>
      this.currencyForm.setValue({
        name: currencyResponseDto.name,
        code: currencyResponseDto.code
      }))
  }

  goBack(): void {
    this._location.back();
  }

}
