import {Component, OnInit} from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {BusinessDirectionApiService} from "../../../../service/business-direction-api.service";
import {HttpParams} from "@angular/common/http";
import {BusinessDirectionResponseDto} from "../../../../model/business-direction-response-dto";

@Component({
  selector: 'app-business-direction-items',
  templateUrl: './business-direction-items.component.html',
  styleUrls: ['./business-direction-items.component.css']
})
export class BusinessDirectionItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  businessDirections: BusinessDirectionResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _businessDirectionApiService: BusinessDirectionApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getBusinessDirections();
  }

  getBusinessDirections(): void {
    this._businessDirectionApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._businessDirectionApiService.getBusinessDirections(this.initHttpParams())
      .subscribe(businessDirections => this.businessDirections = businessDirections);
  }

  deleteById(id: number): void {
    this._businessDirectionApiService.deleteById(id).subscribe(() => {
      this.getBusinessDirections();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getBusinessDirections();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getBusinessDirections();
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

    this.getBusinessDirections();
  }

  createBusinessDirection(): void {
    this._router.navigateByUrl('business_directions/new');
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
