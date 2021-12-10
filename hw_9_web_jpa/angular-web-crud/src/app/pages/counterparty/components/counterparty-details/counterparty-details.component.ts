import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";
import {Router} from "@angular/router";
import {CounterpartyResponseDto} from "../../../../model/counterparty-response-dto";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";

@Component({
  selector: 'app-counterparty-details',
  templateUrl: './counterparty-details.component.html',
  styleUrls: ['./counterparty-details.component.css']
})
export class CounterpartyDetailsComponent implements OnInit {

  id?:number;
  @Input() counterparty?: CounterpartyResponseDto;

  constructor(private route: ActivatedRoute,
              private _counterpartyApiService: CounterpartyApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getCounterparty();
  }

  getCounterparty(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._counterpartyApiService.getCounterparty(this.id)
      .subscribe(counterparty => this.counterparty = counterparty);
  }

  goBack(): void {
    this.location.back();
  }

  goToAgreements(): void {
    this._router.navigate(['/agreements'],{
      queryParams: { counterpartyId: this.id}
    });
  }

  updateCounterparty(): void {
    this._router.navigateByUrl('counterparties/update/' + this.id);
  }

}
