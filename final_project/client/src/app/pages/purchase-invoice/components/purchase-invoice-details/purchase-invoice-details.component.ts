import {Component, Input, OnInit} from '@angular/core';
import {SalesInvoiceResponseDto} from "../../../../model/sales-invoice/sales-invoice-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {SalesInvoiceApiService} from "../../../../service/sales-invoice-api.service";
import {Location} from "@angular/common";
import {PurchaseInvoiceResponseDto} from "../../../../model/purchase-invoice/purchase-invoice-response-dto";
import {PurchaseInvoiceApiService} from "../../../../service/purchase-invoice-api.service";

@Component({
  selector: 'app-purchase-invoice-details',
  templateUrl: './purchase-invoice-details.component.html',
  styleUrls: ['./purchase-invoice-details.component.css']
})
export class PurchaseInvoiceDetailsComponent implements OnInit {

  id?:number;

  @Input() purchaseInvoice?: PurchaseInvoiceResponseDto;

  constructor(private _route: ActivatedRoute,
              private _purchaseInvoiceApiService: PurchaseInvoiceApiService,
              private _location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getSalesInvoice();
  }

  getSalesInvoice(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this._purchaseInvoiceApiService.getPurchaseInvoice(this.id)
      .subscribe(purchaseInvoice => this.purchaseInvoice = purchaseInvoice);
  }

  goBack(): void {
    this._location.back();
  }

  updatePurchaseInvoice(): void {
    this._router.navigateByUrl('purchase_invoices/update/' + this.id);
  }

}
