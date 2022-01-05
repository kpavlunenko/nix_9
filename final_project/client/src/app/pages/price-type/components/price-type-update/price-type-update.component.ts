import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {PriceTypeResponseDto} from "../../../../model/price-type/price-type-response-dto";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";
import {PriceTypeRequestDto} from "../../../../model/price-type/price-type-request-dto";

@Component({
  selector: 'app-price-type-update',
  templateUrl: './price-type-update.component.html',
  styleUrls: ['./price-type-update.component.css']
})
export class PriceTypeUpdateComponent implements OnInit {

  id: number = 0;
  priceType?: PriceTypeRequestDto;
  @Input() priceTypeResponseDto?: Observable<PriceTypeResponseDto>;

  priceTypeForm = new FormGroup({
    name: new FormControl('', Validators.required)
  });

  constructor(private _priceTypeApiService: PriceTypeApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getPriceType();
  }

  update(): void {
    let priceType = this.priceTypeForm.value as PriceTypeRequestDto;
    this._priceTypeApiService.update(this.id, priceType).subscribe(() => {
      this._router.navigateByUrl('price_types');
    });
  }

  getPriceType(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.priceTypeResponseDto = this._priceTypeApiService.getPriceType(this.id);
    this.priceTypeResponseDto.subscribe(priceTypeResponseDto =>
      this.priceTypeForm.setValue({
        name: priceTypeResponseDto.name
      }))
  }

  goBack(): void {
    this._location.back();
  }
}
