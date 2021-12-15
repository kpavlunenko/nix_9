import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CategoryResponseDto} from "../../../../model/category-response-dto";
import {CategoryApiService} from "../../../../service/category-api.service";
import {BankOperationTypeMapping} from "../../../../model/bank-operation-type";

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {

  BankOperationTypeMapping = BankOperationTypeMapping;
  id?: number;

  @Input() category?: CategoryResponseDto;

  constructor(private route: ActivatedRoute,
              private _categoryApiService: CategoryApiService,
              private _location: Location,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getCategory();
  }

  getCategory(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._categoryApiService.getCategory(this.id)
      .subscribe(category => this.category = category);
  }

  goBack(): void {
    this._location.back();
  }

  updateCategory(): void {
    this._router.navigateByUrl('categories/update/' + this.id);
  }

}
