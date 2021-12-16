import { Component, OnInit } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {BankOperationTypeMapping} from "../../../../model/bank-operation-type";
import {BankOperationResponseDto} from "../../../../model/bank-operation-response-dto";
import {BankOperationApiService} from "../../../../service/bank-operation-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-bank-operation-items',
  templateUrl: './bank-operation-items.component.html',
  styleUrls: ['./bank-operation-items.component.css']
})
export class BankOperationItemsComponent implements OnInit {

  BankOperationTypeMapping = BankOperationTypeMapping;
  bankAccountId:string = "";
  userId:string = "";
  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  bankOperations: BankOperationResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'bank account', isActive: false, isSortable: true, sort: 'bankAccount', order: 'asc'},
    {headerName: 'operation type', isActive: false, isSortable: true, sort: 'operationType', order: 'asc'},
    {headerName: 'bank operation type', isActive: false, isSortable: true, sort: 'bankOperationType', order: 'asc'},
    {headerName: 'category', isActive: false, isSortable: true, sort: 'category', order: 'asc'},
    {headerName: 'amount', isActive: false, isSortable: true, sort: 'amount', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''}
    // ,
    // {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}
  ];

  constructor(private _bankOperationApiService: BankOperationApiService,
              private _route: ActivatedRoute,
              private _location: Location,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.parseHttpParams();
    this.getBankOperations();
  }

  getBankOperations(): void {
    this._bankOperationApiService.count(this.initHttpParams())
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

    this._bankOperationApiService.getBankOperations(this.initHttpParams())
      .subscribe(bankOperations => this.bankOperations = bankOperations);
  }

  deleteById(id: number): void {
    this._bankOperationApiService.deleteById(id).subscribe(() => {
      this.getBankOperations();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getBankOperations();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getBankOperations();
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

    this.getBankOperations();
  }

  createBankOperation(): void {
    if (this.bankAccountId != "") {
      this._router.navigate(['bankOperations/new'],{
        queryParams: {bankAccount: this.bankAccountId, user: this.userId}
      });
    } else {
      this._router.navigateByUrl('bankOperations/new');
    }
  }

  initHttpParams(): any {
    return new HttpParams()
      .set('bankAccount', this.bankAccountId)
      .set('sort', this.sort)
      .set('order', this.order)
      .set('currentPage', this.currentPage - 1)
      .set('sizeOfPage', this.sizeOfPage)
      ;
  }

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['bankAccount'] != undefined) {
        this.bankAccountId = params['bankAccount'];
      }
      if (params['user'] != undefined) {
        this.userId = params['user'];
      }
    })
  }

  goBack(): void {
    this._router.navigate(['bankAccounts/details/' + this.bankAccountId],{
      queryParams: {user: this.userId}
    });
  }

}
