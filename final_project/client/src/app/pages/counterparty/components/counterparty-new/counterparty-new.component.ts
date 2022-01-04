import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";

import {FormControl, FormGroup} from "@angular/forms";
import {TypeApiService} from "../../../../service/type-api.service";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {CounterpartyRequestDto} from "../../../../model/counterparty/counterparty-request-dto";

@Component({
  selector: 'app-counterparty-new',
  templateUrl: './counterparty-new.component.html',
  styleUrls: ['./counterparty-new.component.css']
})
export class CounterpartyNewComponent implements OnInit {

  counterparty?: CounterpartyRequestDto;
  counterpartyTypes?: string[];

  counterpartyForm = new FormGroup({
    name: new FormControl(''),
    inn: new FormControl(''),
    counterpartyType: new FormControl('')
  });

  constructor(private _counterpartyApiService: CounterpartyApiService,
              private _typeApiService: TypeApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getCounterpartyTypes();
  }

  create(): void {
    let counterparty = this.counterpartyForm.value as CounterpartyRequestDto;

    this._counterpartyApiService.create(counterparty).subscribe(() => {
      this._router.navigateByUrl('counterparties');
    });
  }

  getCounterpartyTypes(): void {
    this._typeApiService.getTypes('counterpartyTypes')
      .subscribe(counterpartyTypes => this.counterpartyTypes = counterpartyTypes);
  }

  goBack(): void {
    this.location.back();
  }
}
