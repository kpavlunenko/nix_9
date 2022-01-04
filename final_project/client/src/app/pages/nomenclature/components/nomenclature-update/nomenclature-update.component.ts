import {Component, Input, OnInit} from '@angular/core';
import {BusinessDirectionResponseDto} from "../../../../model/business-direction/business-direction-response-dto";
import {Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {NomenclatureRequestDto} from "../../../../model/nomenclature/nomenclature-request-dto";
import {NomenclatureResponseDto} from "../../../../model/nomenclature/nomenclature-response-dto";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";

@Component({
  selector: 'app-nomenclature-update',
  templateUrl: './nomenclature-update.component.html',
  styleUrls: ['./nomenclature-update.component.css']
})
export class NomenclatureUpdateComponent implements OnInit {

  id: number = 0;
  nomenclature?: NomenclatureRequestDto;
  businessDirections?: BusinessDirectionResponseDto[];
  @Input() nomenclatureResponseDto?: Observable<NomenclatureResponseDto>;

  isProduct: string = 'true';

  nomenclatureForm = new FormGroup({
    name: new FormControl('', Validators.required),
    businessDirectionId: new FormControl(''),
    isProduct: new FormControl('')
  });

  constructor(private _nomenclatureApiService: NomenclatureApiService,
              private _businessDirectionApiService: BusinessDirectionApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getNomenclature();
    this.getBusinessDirections();
  }

  getBusinessDirections(): void {
    this._businessDirectionApiService.getBusinessDirections(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(businessDirections => this.businessDirections = businessDirections);
  }

  update(): void {
    let nomenclature = this.nomenclatureForm.value as NomenclatureRequestDto;
    if (this.isProduct == 'true') {
      nomenclature.service = false;
      nomenclature.product = true;
    } else {
      nomenclature.service = true;
      nomenclature.product = false;
    }
    this._nomenclatureApiService.update(this.id, nomenclature).subscribe(() => {
      this._router.navigateByUrl('nomenclatures');
    });
  }

  getNomenclature(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.nomenclatureResponseDto = this._nomenclatureApiService.getNomenclature(this.id);
    this.nomenclatureResponseDto.subscribe(nomenclatureResponseDto =>
      this.nomenclatureForm.setValue({
        name: nomenclatureResponseDto.name,
        isProduct: '' + nomenclatureResponseDto.product,
        businessDirectionId: nomenclatureResponseDto.businessDirection.id
      }))
  }

  goBack(): void {
    this._location.back();
  }

}
