import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {PriceResponseDto} from "../../../../model/prices/price-response-dto";
import {PriceApiService} from "../../../../service/price-api.service";

@Component({
  selector: 'app-price-details',
  templateUrl: './price-details.component.html',
  styleUrls: ['./price-details.component.css']
})
export class PriceDetailsComponent implements OnInit {

  id?:number;

  @Input() price?: PriceResponseDto;

  constructor(private _route: ActivatedRoute,
              private _priceApiService: PriceApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getPrice();
  }

  getPrice(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._priceApiService.getPrice(this.id)
      .subscribe(price => this.price = price);
  }

  goBack(): void {
    this._location.back();
  }

  updatePrice(): void {
    this._router.navigateByUrl('prices/update/' + this.id);
  }
}
