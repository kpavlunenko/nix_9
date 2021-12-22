import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";
import {NomenclatureResponseDto} from "../../../../model/nomenclature-response-dto";

@Component({
  selector: 'app-nomenclature-details',
  templateUrl: './nomenclature-details.component.html',
  styleUrls: ['./nomenclature-details.component.css']
})
export class NomenclatureDetailsComponent implements OnInit {

  id?:number;

  @Input() nomenclature?: NomenclatureResponseDto;

  constructor(private _route: ActivatedRoute,
              private _nomenclatureApiService: NomenclatureApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getNomenclature();
  }

  getNomenclature(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._nomenclatureApiService.getNomenclature(this.id)
      .subscribe(nomenclature => this.nomenclature = nomenclature);
  }

  goBack(): void {
    this._location.back();
  }

  updateNomenclature(): void {
    this._router.navigateByUrl('nomenclatures/update/' + this.id);
  }

}
