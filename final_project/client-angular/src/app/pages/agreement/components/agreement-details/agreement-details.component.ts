import {Component, Input, OnInit} from '@angular/core';
import {AgreementResponseDto} from "../../../../model/agreement-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {AgreementApiService} from "../../../../service/agreement-api.service";

@Component({
  selector: 'app-agreement-details',
  templateUrl: './agreement-details.component.html',
  styleUrls: ['./agreement-details.component.css']
})
export class AgreementDetailsComponent implements OnInit {

  id?:number;

  @Input() agreement?: AgreementResponseDto;

  constructor(private route: ActivatedRoute,
              private _agreementApiService: AgreementApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getAgreement();
  }

  getAgreement(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._agreementApiService.getAgreement(this.id)
      .subscribe(agreement => this.agreement = agreement);
  }

  goBack(): void {
    this.location.back();
  }

  updateAgreement(): void {
    this._router.navigateByUrl('agreements/update/' + this.id);
  }

}
