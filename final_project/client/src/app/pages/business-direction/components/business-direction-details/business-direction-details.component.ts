import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {BusinessDirectionResponseDto} from "../../../../model/business-direction/business-direction-response-dto";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";

@Component({
  selector: 'app-business-direction-details',
  templateUrl: './business-direction-details.component.html',
  styleUrls: ['./business-direction-details.component.css']
})
export class BusinessDirectionDetailsComponent implements OnInit {

  id?:number;

  @Input() businessDirection?: BusinessDirectionResponseDto;

  constructor(private route: ActivatedRoute,
              private _businessDirectionApiService: BusinessDirectionApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getBusinessDirection();
  }

  getBusinessDirection(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._businessDirectionApiService.getBusinessDirection(this.id)
      .subscribe(businessDirection => this.businessDirection = businessDirection);
  }

  goBack(): void {
    this.location.back();
  }

  updateBusinessDirection(): void {
    this._router.navigateByUrl('business_directions/update/' + this.id);
  }

}
