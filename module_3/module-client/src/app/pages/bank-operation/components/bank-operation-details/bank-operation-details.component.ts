import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BankOperationResponseDto} from "../../../../model/bank-operation-response-dto";
import {BankOperationApiService} from "../../../../service/bank-operation-api.service";
import {BankOperationTypeMapping} from "../../../../model/bank-operation-type";

@Component({
  selector: 'app-bank-operation-details',
  templateUrl: './bank-operation-details.component.html',
  styleUrls: ['./bank-operation-details.component.css']
})
export class BankOperationDetailsComponent implements OnInit {

  id?: number;
  BankOperationTypeMapping = BankOperationTypeMapping;

  @Input() bankOperation?: BankOperationResponseDto;

  constructor(private route: ActivatedRoute,
              private _bankOperationApiService: BankOperationApiService,
              private _location: Location,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getBankOperation();
  }

  getBankOperation(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._bankOperationApiService.getBankOperation(this.id)
      .subscribe(bankOperation => this.bankOperation = bankOperation);
  }

  goBack(): void {
    this._location.back();
  }

}
