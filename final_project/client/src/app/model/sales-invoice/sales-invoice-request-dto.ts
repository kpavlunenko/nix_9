import {SalesInvoiceGoodRequestDto} from "./sales-invoice-good-request-dto";

export interface SalesInvoiceRequestDto {

  date: Date;
  companyId: number;
  counterpartyId: number;
  agreementId: number;
  currencyId: number;
  priceTypeId: number;
  salesInvoiceGoods: SalesInvoiceGoodRequestDto[];
}
