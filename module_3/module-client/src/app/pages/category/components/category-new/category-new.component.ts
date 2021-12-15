import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CategoryRequestDto} from "../../../../model/category-request-dto";
import {CategoryApiService} from "../../../../service/category-api.service";
import {BankOperationType, BankOperationTypeMapping} from "../../../../model/bank-operation-type";

@Component({
  selector: 'app-category-new',
  templateUrl: './category-new.component.html',
  styleUrls: ['./category-new.component.css']
})
export class CategoryNewComponent implements OnInit {

  category?: CategoryRequestDto;
  BankOperationTypeMapping = BankOperationTypeMapping;
  BankOperationType = BankOperationType;

  categoryForm = new FormGroup({
    name: new FormControl('', Validators.required),
    bankOperationType: new FormControl(''),
  });

  constructor(private _categoryApiService: CategoryApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
  }

  create(): void {
    let category = this.categoryForm.value as CategoryRequestDto;

    this._categoryApiService.create(category).subscribe(() => {
      this._router.navigateByUrl('categories');
    });
  }

  goBack(): void {
    this._location.back();
  }

}
