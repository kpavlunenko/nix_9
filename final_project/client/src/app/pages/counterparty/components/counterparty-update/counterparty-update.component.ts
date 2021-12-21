import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {Observable} from "rxjs";
import {CounterpartyResponseDto} from "../../../../model/counterparty-response-dto";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {CounterpartyRequestDto} from "../../../../model/counterparty-request-dto";

@Component({
  selector: 'app-counterparty-update',
  templateUrl: './counterparty-update.component.html',
  styleUrls: ['./counterparty-update.component.css']
})
export class CounterpartyUpdateComponent implements OnInit {

  id: number = 0;
  counterparty?: CounterpartyRequestDto;
  counterpartyTypes?: string[];
  @Input() counterpartyResponseDto?: Observable<CounterpartyResponseDto>;

  counterpartyForm = new FormGroup({
    name: new FormControl(''),
    inn: new FormControl(''),
    counterpartyType: new FormControl('')
  });

  constructor(private _counterpartyApiService: CounterpartyApiService,
              private _typeApiService: TypeApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCounterpartyTypes();
    this.getCounterparty();
  }

  update(): void {
    let counterparty = this.counterpartyForm.value as CounterpartyRequestDto;

    this._counterpartyApiService.update(this.id, counterparty).subscribe(() => {
      this._router.navigateByUrl('counterparties');
    });
  }

  getCounterparty(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.counterpartyResponseDto = this._counterpartyApiService.getCounterparty(this.id);
    this.counterpartyResponseDto.subscribe(counterpartyResponseDto =>
      this.counterpartyForm.setValue({
        name: counterpartyResponseDto.name,
        inn: counterpartyResponseDto.inn,
        counterpartyType: counterpartyResponseDto.counterpartyType
      }))
  }

  getCounterpartyTypes(): void {
    this._typeApiService.getTypes('counterpartyTypes')
      .subscribe(counterpartyTypes => this.counterpartyTypes = counterpartyTypes);
  }

  goBack(): void {
    this.location.back();
  }

}
