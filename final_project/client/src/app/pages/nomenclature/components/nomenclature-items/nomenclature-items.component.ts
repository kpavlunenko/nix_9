import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {NomenclatureResponseDto} from "../../../../model/nomenclature-response-dto";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";

@Component({
  selector: 'app-nomenclature-items',
  templateUrl: './nomenclature-items.component.html',
  styleUrls: ['./nomenclature-items.component.css']
})
export class NomenclatureItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  nomenclatures: NomenclatureResponseDto[] = [];
  headers: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'product', isActive: false, isSortable: true, sort: 'product', order: 'asc'},
    {headerName: 'service', isActive: false, isSortable: true, sort: 'service', order: 'asc'},
    {headerName: 'business direction', isActive: false, isSortable: true, sort: 'businessDirection', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _nomenclatureApiService: NomenclatureApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getNomenclatures();
  }

  getNomenclatures(): void {
    this._nomenclatureApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._nomenclatureApiService.getNomenclatures(this.initHttpParams())
      .subscribe(nomenclatures => this.nomenclatures = nomenclatures);
  }

  deleteById(id: number): void {
    this._nomenclatureApiService.deleteById(id).subscribe(() => {
      this.getNomenclatures();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getNomenclatures();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getNomenclatures();
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

    this.getNomenclatures();
  }

  createNomenclature(): void {
    this._router.navigateByUrl('nomenclatures/new');
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
