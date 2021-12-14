import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {CategoryResponseDto} from "../../../../model/category-response-dto";
import {CategoryApiService} from "../../../../service/category-api.service";
import {BankOperationType, BankOperationTypeMapping} from "../../../../model/bank-operation-type";

@Component({
  selector: 'app-category-items',
  templateUrl: './category-items.component.html',
  styleUrls: ['./category-items.component.css']
})
export class CategoryItemsComponent implements OnInit {

  BankOperationTypeMapping = BankOperationTypeMapping;
  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  categories: CategoryResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'bank operation type', isActive: false, isSortable: true, sort: 'BankOperationType', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _categoryApiService: CategoryApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this._categoryApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
          if (this.totalPageSize == 0) {
            this.totalPageSize = 1;
          }
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._categoryApiService.getCategories(this.initHttpParams())
      .subscribe(categories => this.categories = categories);
  }

  deleteById(id: number): void {
    this._categoryApiService.deleteById(id).subscribe(() => {
      this.getCategories();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getCategories();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getCategories();
  }

  sortOn(sort: string, order: string) {
    // @ts-ignore
    this.headers.forEach(function (header) {
      if (header.sort == sort) {
        header.isActive = true;
        header.order = order;
      } else {
        header.isActive = false;
      }
    })

    this.sort = sort;
    this.order = order;

    this.getCategories();
  }

  createCategory(): void {
    this._router.navigateByUrl('categories/new');
  }

  initHttpParams(): any {
    return new HttpParams()
      .set('sort', this.sort)
      .set('order', this.order)
      .set('currentPage', this.currentPage - 1)
      .set('sizeOfPage', this.sizeOfPage)
      ;
  }

}
