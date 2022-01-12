import {PurchaseInvoiceGoodRequestDto} from "./purchase-invoice-good-request-dto";

export interface PurchaseInvoiceRequestDto {

  date: Date;
  companyId: number;
  counterpartyId: number;
  agreementId: number;
  currencyId: number;
  purchaseInvoiceGoods: PurchaseInvoiceGoodRequestDto[];
}
