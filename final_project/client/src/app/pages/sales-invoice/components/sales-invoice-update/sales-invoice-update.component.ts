import {Component, Input, OnInit} from '@angular/core';
import {CompanyShortResponseDto} from "../../../../model/company/company-short-response-dto";
import {CounterpartyResponseDto} from "../../../../model/counterparty/counterparty-response-dto";
import {AgreementResponseDto} from "../../../../model/agreement/agreement-response-dto";
import {PriceTypeResponseDto} from "../../../../model/price-type/price-type-response-dto";
import {CurrencyResponseDto} from "../../../../model/currency/currency-response-dto";
import {NomenclatureResponseDto} from "../../../../model/nomenclature/nomenclature-response-dto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {SalesInvoiceApiService} from "../../../../service/sales-invoice-api.service";
import {CompanyApiService} from "../../../../service/company-api.service";
import {CounterpartyApiService} from "../../../../service/counterparty-api.service";
import {AgreementApiService} from "../../../../service/agreement-api.service";
import {PriceTypeApiService} from "../../../../service/price-type-api.service";
import {PriceApiService} from "../../../../service/price-api.service";
import {CurrencyApiService} from "../../../../service/currency-api.service";
import {NomenclatureApiService} from "../../../../service/nomenclature-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {formatDate, Location} from "@angular/common";
import {HttpParams} from "@angular/common/http";
import {SalesInvoiceRequestDto} from "../../../../model/sales-invoice/sales-invoice-request-dto";
import {Observable} from "rxjs";
import {SalesInvoiceResponseDto} from "../../../../model/sales-invoice/sales-invoice-response-dto";

@Component({
  selector: 'app-sales-invoice-update',
  templateUrl: './sales-invoice-update.component.html',
  styleUrls: ['./sales-invoice-update.component.css']
})
export class SalesInvoiceUpdateComponent implements OnInit {

  id: number = 0;
  @Input() salesInvoiceResponseDto?: Observable<SalesInvoiceResponseDto>;
  currentDate: Date = new Date();
  companies?: CompanyShortResponseDto[];
  counterparties?: CounterpartyResponseDto[];
  agreements?: AgreementResponseDto[];
  priceTypes?: PriceTypeResponseDto[];
  currencies?: CurrencyResponseDto[];
  nomenclatures?: NomenclatureResponseDto[];
  salesInvoiceForm = new FormGroup({
    date: new FormControl(new Date().toISOString().slice(0, 16)),
    companyId: new FormControl(''),
    counterpartyId: new FormControl(''),
    agreementId: new FormControl(''),
    currencyId: new FormControl(''),
    priceTypeId: new FormControl(''),
    salesInvoiceGoods: new FormArray([
      new FormGroup({
        nomenclatureId: new FormControl(''),
        price: new FormControl(''),
        quantity: new FormControl(''),
        sum: new FormControl('')
      })
    ])
  });

  constructor(private _salesInvoiceApiService: SalesInvoiceApiService,
              private _companyApiService: CompanyApiService,
              private _counterpartyApiService: CounterpartyApiService,
              private _agreementApiService: AgreementApiService,
              private _priceTypeApiService: PriceTypeApiService,
              private _priceApiService: PriceApiService,
              private _currencyApiService: CurrencyApiService,
              private _nomenclatureApiService: NomenclatureApiService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getSalesInvoice();
    this.getCompanies();
    this.getCounterparties();
    this.getAgreements();
    this.getPriceTypes();
    this.getCurrencies();
    this.getNomenclatures();

  }

  getFormsControls(): FormArray {
    return this.salesInvoiceForm.controls['salesInvoiceGoods'] as FormArray;
  }

  addRow() {
    (<FormArray>this.salesInvoiceForm.controls["salesInvoiceGoods"]).push(new FormGroup({
      nomenclatureId: new FormControl(''),
      price: new FormControl(''),
      quantity: new FormControl(''),
      sum: new FormControl('')
    }));
  }

  removeRow(index: number) {
    (<FormArray>this.salesInvoiceForm.controls["salesInvoiceGoods"]).removeAt(index);
  }

  getSalesInvoice(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.salesInvoiceResponseDto = this._salesInvoiceApiService.getSalesInvoice(this.id);
    this.salesInvoiceResponseDto.subscribe(salesInvoiceResponseDto =>
      this.salesInvoiceForm.setValue({
        date: salesInvoiceResponseDto.date.toString().slice(0, 16),
        companyId: salesInvoiceResponseDto.company.id,
        counterpartyId: salesInvoiceResponseDto.counterparty.id,
        agreementId: salesInvoiceResponseDto.agreement.id,
        currencyId: salesInvoiceResponseDto.currency.id,
        priceTypeId: salesInvoiceResponseDto.priceType.id,
        salesInvoiceGoods: [{
          nomenclatureId: new FormControl(''),
          price: new FormControl(''),
          quantity: new FormControl(''),
          sum: new FormControl('')}]
      }))
    this.salesInvoiceResponseDto.subscribe(salesInvoiceResponseDto => {
      this.removeRow(0);
      salesInvoiceResponseDto.salesInvoiceGoods.forEach(value => {
      (<FormArray>this.salesInvoiceForm.controls["salesInvoiceGoods"]).push(new FormGroup({
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

  getPriceTypes(): void {
    this._priceTypeApiService.getPriceTypes(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(priceTypes => this.priceTypes = priceTypes);
  }

  getAgreements(): void {
    this._agreementApiService.getAgreements(new HttpParams()
      .set('agreementType', 'CLIENT_AGREEMENT')
      .set('company', this.salesInvoiceForm.value.companyId)
      .set('counterparty', this.salesInvoiceForm.value.counterpartyId)
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
    let salesInvoice = this.salesInvoiceForm.value as SalesInvoiceRequestDto;

    this._salesInvoiceApiService.create(salesInvoice).subscribe(() => {
      this._router.navigateByUrl('sales_invoices');
    });
  }

  companyOnChange(): void {
    this.getAgreements();
  }

  counterpartyOnChange(): void {
    this.getAgreements();
  }

  priceTypeOnChange(): void {
    for (let i = 0; i < this.salesInvoiceForm.value.salesInvoiceGoods.length; i++) {
      this.nomenclatureOnChange(i);
    }
  }

  nomenclatureOnChange(rowId: number): void {
    this._priceApiService.getNomenclaturePrice(new HttpParams()
      .set('nomenclature', this.salesInvoiceForm.value.salesInvoiceGoods[rowId].nomenclatureId)
      .set('priceType', this.salesInvoiceForm.value.priceTypeId)
      .set('date', Date.parse(this.salesInvoiceForm.value.date))
    )
      .subscribe(prices => {
        let price = Number(prices.price);
        this.salesInvoiceForm.value.salesInvoiceGoods[rowId].price = price;
        this.recalculateRow(rowId);
      });
  }

  priceOnChange(rowId: number): void {
    this.recalculateRow(rowId);
  }

  quantityOnChange(rowId: number): void {
    this.recalculateRow(rowId);
  }

  recalculateRow(rowId: number): void {
    let price = this.salesInvoiceForm.value.salesInvoiceGoods[rowId].price;
    let quantity = this.salesInvoiceForm.value.salesInvoiceGoods[rowId].quantity;
    let sum = quantity * price;

    (<FormArray>this.salesInvoiceForm.controls["salesInvoiceGoods"]).setControl(rowId, new FormGroup({
      nomenclatureId: new FormControl(this.salesInvoiceForm.value.salesInvoiceGoods[rowId].nomenclatureId),
      price: new FormControl(price),
      quantity: new FormControl(quantity),
      sum: new FormControl(sum)
    }))
  }

  goBack(): void {
    this._location.back();
  }
}
