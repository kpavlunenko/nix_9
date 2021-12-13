import {Component, OnInit} from '@angular/core';
import {UserResponseDto} from "../../../../model/user-response-dto";
import {TableHeader} from "../../../../model/table-header";
import {UserApiService} from "../../../../service/user-api.service";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-user-items',
  templateUrl: './user-items.component.html',
  styleUrls: ['./user-items.component.css']
})
export class UserItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  users: UserResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'first name', isActive: false, isSortable: true, sort: 'firstName', order: 'asc'},
    {headerName: 'last name', isActive: false, isSortable: true, sort: 'lastName', order: 'asc'},
    {headerName: 'email', isActive: false, isSortable: true, sort: 'email', order: 'asc'},
    {headerName: 'phone', isActive: false, isSortable: true, sort: 'phone', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _userApiService: UserApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this._userApiService.count(this.initHttpParams())
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

    this._userApiService.getUsers(this.initHttpParams())
      .subscribe(users => this.users = users);
  }

  deleteById(id: number): void {
    this._userApiService.deleteById(id).subscribe(() => {
      this.getUsers();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getUsers();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getUsers();
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

    this.getUsers();
  }

  createUser(): void {
    this._router.navigateByUrl('users/new');
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
