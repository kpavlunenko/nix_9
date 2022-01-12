import {Component, Input, OnInit} from '@angular/core';
import {CompanyShortResponseDto} from "../../../../model/company/company-short-response-dto";
import {CounterpartyResponseDto} from "../../../../model/counterparty/counterparty-response-dto";
import {AgreementResponseDto} from "../../../../model/agreement/agreement-response-dto";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {NomenclatureResponseDto} from "../../../../model/nomenclature/nomenclature-response-dto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {PurchaseInvoiceApiService} from "../../../../service/purchase-invoice-api.service";
import {CompanyApiService} from "../../../../service/company-api.service";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {AgreementApiService} from "../../../../service/agreement-api.service";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {PurchaseInvoiceRequestDto} from "../../../../model/purchase-invoice/purchase-invoice-request-dto";
import {Observable} from "rxjs";
import {SalesInvoiceResponseDto} from "../../../../model/sales-invoice/sales-invoice-response-dto";
import {PurchaseInvoiceResponseDto} from "../../../../model/purchase-invoice/purchase-invoice-response-dto";

@Component({
  selector: 'app-purchase-invoice-update',
  templateUrl: './purchase-invoice-update.component.html',
  styleUrls: ['./purchase-invoice-update.component.css']
})
export class PurchaseInvoiceUpdateComponent implements OnInit {

  id: number = 0;
  @Input() purchaseInvoiceResponseDto?: Observable<PurchaseInvoiceResponseDto>;

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
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getPurchaseInvoice();
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

  getPurchaseInvoice(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.purchaseInvoiceResponseDto = this._purchaseInvoiceApiService.getPurchaseInvoice(this.id);
    this.purchaseInvoiceResponseDto.subscribe(purchaseInvoiceResponseDto =>
      this.purchaseInvoiceForm.setValue({
        date: purchaseInvoiceResponseDto.date.toString().slice(0, 16),
        companyId: purchaseInvoiceResponseDto.company.id,
        counterpartyId: purchaseInvoiceResponseDto.counterparty.id,
        agreementId: purchaseInvoiceResponseDto.agreement.id,
        currencyId: purchaseInvoiceResponseDto.currency.id,
        purchaseInvoiceGoods: [{
          nomenclatureId: new FormControl(''),
          price: new FormControl(''),
          quantity: new FormControl(''),
          sum: new FormControl('')}]
      }))
    this.purchaseInvoiceResponseDto.subscribe(purchaseInvoiceResponseDto => {
      this.removeRow(0);
      purchaseInvoiceResponseDto.purchaseInvoiceGoods.forEach(value => {
        (<FormArray>this.purchaseInvoiceForm.controls["purchaseInvoiceGoods"]).push(new FormGroup({
          nomenclatureId: new FormControl(value.nomenclature.id),
          price: new FormControl(value.price),
          quantity: new FormControl(value.quantity),
          sum: new FormControl(value.sum)
        }))
      })})
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

  update(): void {
    let purchaseInvoice = this.purchaseInvoiceForm.value as PurchaseInvoiceRequestDto;

    this._purchaseInvoiceApiService.update(this.id, purchaseInvoice).subscribe(() => {
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
