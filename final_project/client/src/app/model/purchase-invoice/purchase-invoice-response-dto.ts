import {ResponseDto} from "../response-dto";
import {CompanyShortResponseDto} from "../company/company-short-response-dto";
import {CounterpartyResponseDto} from "../counterparty/counterparty-response-dto";
import {AgreementResponseDto} from "../agreement/agreement-response-dto";
import {CurrencyResponseDto} from "../currency/currency-response-dto";
import {PurchaseInvoiceGoodResponseDto} from "./purchase-invoice-good-response-dto";

export interface PurchaseInvoiceResponseDto extends ResponseDto {

  date: Date;
  company: CompanyShortResponseDto;
  counterparty: CounterpartyResponseDto;
  agreement: AgreementResponseDto;
  currency: CurrencyResponseDto;
  sum: number;
  purchaseInvoiceGoods: PurchaseInvoiceGoodResponseDto[];
}
