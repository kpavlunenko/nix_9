import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CategoryRequestDto} from "../../../../model/category-request-dto";
import {CategoryResponseDto} from "../../../../model/category-response-dto";
import {CategoryApiService} from "../../../../service/category-api.service";
import {BankOperationType, BankOperationTypeMapping} from "../../../../model/bank-operation-type";

@Component({
  selector: 'app-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.css']
})
export class CategoryUpdateComponent implements OnInit {

  id: number = 0;
  category?: CategoryRequestDto;
  @Input() categoryResponseDto?: Observable<CategoryResponseDto>;
  BankOperationTypeMapping = BankOperationTypeMapping;
  public BankOperationType = BankOperationType;

  categoryForm = new FormGroup({
    name: new FormControl('', Validators.required),
    bankOperationType: new FormControl(''),
  });

  constructor(private _categoryApiService: CategoryApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCategory();
  }

  update(): void {
    let category = this.categoryForm.value as CategoryRequestDto;

    this._categoryApiService.update(this.id, category).subscribe(() => {
      this._router.navigateByUrl('categories');
    });
  }

  getCategory(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.categoryResponseDto = this._categoryApiService.getCategory(this.id);
    this.categoryResponseDto.subscribe(categoryResponseDto =>
      this.categoryForm.setValue({
        name: categoryResponseDto.name,
        bankOperationType: categoryResponseDto.bankOperationType
      }))
  }

  goBack(): void {
    this._location.back();
  }

}
