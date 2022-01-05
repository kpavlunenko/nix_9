import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {PriceTypeRequestDto} from "../../../../model/price-type/price-type-request-dto";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";

@Component({
  selector: 'app-price-type-new',
  templateUrl: './price-type-new.component.html',
  styleUrls: ['./price-type-new.component.css']
})
export class PriceTypeNewComponent implements OnInit {

  priceTypeForm = new FormGroup({
    name: new FormControl('')
  });


  constructor(private _priceTypeApiService: PriceTypeApiService,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
  }

  create(): void {
    let priceType = this.priceTypeForm.value as PriceTypeRequestDto;

    this._priceTypeApiService.create(priceType).subscribe(() => {
      this._router.navigateByUrl('price_types');
    });
  }

  goBack(): void {
    this._location.back();
  }

}
