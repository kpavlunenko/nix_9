import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TableHeader} from "../../../../model/table-header";
import {HttpParams} from "@angular/common/http";
import {CounterpartyResponseDto} from "../../../../model/counterparty/counterparty-response-dto";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";

@Component({
  selector: 'app-counterparty-items',
  templateUrl: './counterparty-items.component.html',
  styleUrls: ['./counterparty-items.component.css']
})
export class CounterpartyItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  counterparties: CounterpartyResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'counterparty type', isActive: false, isSortable: true, sort: 'counterpartyType', order: 'asc'},
    {headerName: 'inn', isActive: false, isSortable: true, sort: 'inn', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _counterpartyApiService: CounterpartyApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getCounterparties();
  }

  getCounterparties(): void {
    this._counterpartyApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._counterpartyApiService.getCounterparties(this.initHttpParams())
      .subscribe(counterparties => this.counterparties = counterparties);
  }

  deleteById(id: number): void {
    this._counterpartyApiService.deleteById(id).subscribe(() => {
      this.getCounterparties();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getCounterparties();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getCounterparties();
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

    this.getCounterparties();
  }

  createCounterparty(): void {
    this._router.navigateByUrl('counterparties/new');
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
