import { Component, OnInit } from '@angular/core';
import {CompanyShortResponseDto} from "../../../../model/company/company-short-response-dto";
import {CounterpartyResponseDto} from "../../../../model/counterparty/counterparty-response-dto";
import {AgreementResponseDto} from "../../../../model/agreement/agreement-response-dto";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {NomenclatureResponseDto} from "../../../../model/nomenclature/nomenclature-response-dto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {CompanyApiService} from "../../../../service/company-api.service";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {AgreementApiService} from "../../../../service/agreement-api.service";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {PurchaseInvoiceApiService} from "../../../../service/purchase-invoice-api.service";
import {PurchaseInvoiceRequestDto} from "../../../../model/purchase-invoice/purchase-invoice-request-dto";

@Component({
  selector: 'app-purchase-invoice-new',
  templateUrl: './purchase-invoice-new.component.html',
  styleUrls: ['./purchase-invoice-new.component.css']
})
export class PurchaseInvoiceNewComponent implements OnInit {

  currentDate: Date = new Date();
  companies?: CompanyShortResponseDto[];
  counterparties?: CounterpartyResponseDto[];
  agreements?: AgreementResponseDto[];
  currencies?: CurrencyResponseDto[];
  nomenclatures?: NomenclatureResponseDto[];
  purchaseInvoiceForm = new FormGroup({
    date: new FormControl(new Date().toISOString().slice(0, 16)),
    companyId: new FormControl(''),
    counterpartyId: new FormControl(''),
    agreementId: new FormControl(''),
    currencyId: new FormControl(''),
    purchaseInvoiceGoods: new FormArray([
      new FormGroup({
        nomenclatureId: new FormControl(''),
        price: new FormControl(''),
        quantity: new FormControl(''),
        sum: new FormControl('')
      })
    ])
  });

  constructor(private _purchaseInvoiceApiService: PurchaseInvoiceApiService,
              private _companyApiService: CompanyApiService,
              private _counterpartyApiService: CounterpartyApiService,
              private _agreementApiService: AgreementApiService,
              private _currencyApiService: CurrencyApiService,
              private _nomenclatureApiService: NomenclatureApiService,
              private _router: Router,
              private _location: Location) {
  }

  ngOnInit(): void {
    this.getCompanies();
    this.getCounterparties();
    this.getAgreements();
    this.getCurrencies();
    this.getNomenclatures();
  }

  getFormsControls(): FormArray {
    return this.purchaseInvoiceForm.controls['purchaseInvoiceGoods'] as FormArray;
  }

  addRow() {
    (<FormArray>this.purchaseInvoiceForm.controls["purchaseInvoiceGoods"]).push(new FormGroup({
      nomenclatureId: new FormControl(''),
      price: new FormControl(''),
      quantity: new FormControl(''),
      sum: new FormControl('')
    }));
  }

  removeRow(index: number) {
    (<FormArray>this.purchaseInvoiceForm.controls["purchaseInvoiceGoods"]).removeAt(index);
  }

  getNomenclatures(): void {
    this._nomenclatureApiService.getNomenclatures(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(nomenclatures => this.nomenclatures = nomenclatures);
  }

  getCurrencies(): void {
    this._currencyApiService.getCurrencies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(currencies => this.currencies = currencies);
  }

  getAgreements(): void {
    this._agreementApiService.getAgreements(new HttpParams()
      .set('agreementType', 'SUPPLIER_AGREEMENT')
      .set('company', this.purchaseInvoiceForm.value.companyId)
      .set('counterparty', this.purchaseInvoiceForm.value.counterpartyId)
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(agreements => this.agreements = agreements);
  }

  getCounterparties(): void {
    this._counterpartyApiService.getCounterparties(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(counterparties => this.counterparties = counterparties);
  }

  getCompanies(): void {
    this._companyApiService.getCompanies(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(companies => this.companies = companies);
  }

  create(): void {
    let purchaseInvoice = this.purchaseInvoiceForm.value as PurchaseInvoiceRequestDto;

    this._purchaseInvoiceApiService.create(purchaseInvoice).subscribe(() => {
      this._router.navigateByUrl('purchase_invoices');
    });
  }

  companyOnChange(): void {
    this.getAgreements();
  }

  counterpartyOnChange(): void {
    this.getAgreements();
  }

  priceOnChange(rowId: number): void {
    this.recalculateRow(rowId);
  }

  quantityOnChange(rowId: number): void {
    this.recalculateRow(rowId);
  }

  recalculateRow(rowId: number): void {
    let price = this.purchaseInvoiceForm.value.purchaseInvoiceGoods[rowId].price;
    let quantity = this.purchaseInvoiceForm.value.purchaseInvoiceGoods[rowId].quantity;
    let sum = quantity * price;

    (<FormArray>this.purchaseInvoiceForm.controls["purchaseInvoiceGoods"]).setControl(rowId, new FormGroup({
      nomenclatureId: new FormControl(this.purchaseInvoiceForm.value.purchaseInvoiceGoods[rowId].nomenclatureId),
      price: new FormControl(price),
      quantity: new FormControl(quantity),
      sum: new FormControl(sum)
    }))
  }

  goBack(): void {
    this._location.back();
  }
}
