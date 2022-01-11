import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {SalesInvoiceResponseDto} from "../../../../model/sales-invoice/sales-invoice-response-dto";
import {SalesInvoiceApiService} from "../../../../service/sales-invoice-api.service";

@Component({
  selector: 'app-sales-invoice-details',
  templateUrl: './sales-invoice-details.component.html',
  styleUrls: ['./sales-invoice-details.component.css']
})
export class SalesInvoiceDetailsComponent implements OnInit {

  id?:number;

  @Input() salesInvoice?: SalesInvoiceResponseDto;

  constructor(private _route: ActivatedRoute,
              private _salesInvoiceApiService: SalesInvoiceApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getSalesInvoice();
  }

  getSalesInvoice(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._salesInvoiceApiService.getSalesInvoice(this.id)
      .subscribe(salesInvoice => this.salesInvoice = salesInvoice);
  }

  goBack(): void {
    this._location.back();
  }

  updateSalesInvoice(): void {
    this._router.navigateByUrl('sales_invoices/update/' + this.id);
  }

}
