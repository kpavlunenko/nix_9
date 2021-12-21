import { Component, OnInit, Input } from '@angular/core';
import {TableHeader} from "../../../../model/table-header";

@Component({
  selector: 'app-table-header',
  templateUrl: './table-header.component.html',
  styleUrls: ['./table-header.component.css']
})
export class TableHeaderComponent implements OnInit {

  @Input() headers?: TableHeader[];
  constructor() { }

  ngOnInit(): void {
  }

  sortOn(sort: string, order: string) {
    // @ts-ignore
    // this.headers.forEach(function (header) {
    //   if (header.sort == sort) {
    //     header.isActive = true;
    //     header.order = order;
    //   } else {
    //     header.isActive = false;
    //   }
    // })
    //
    // this.sort = sort;
    // this.order = order;
    //
    // this.getCompanies();
  }
}
