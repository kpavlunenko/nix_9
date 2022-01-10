import {ResponseDto} from "../response-dto";
import {SalesInvoiceGoodResponseDto} from "./sales-invoice-good-response-dto";
import {CompanyShortResponseDto} from "../company/company-short-response-dto";
import {CounterpartyResponseDto} from "../counterparty/counterparty-response-dto";
import {AgreementResponseDto} from "../agreement/agreement-response-dto";
import {CurrencyResponseDto} from "../currency/currency-response-dto";
import {PriceTypeResponseDto} from "../price-type/price-type-response-dto";

export interface SalesInvoiceResponseDto extends ResponseDto {

  date: Date;
  company: CompanyShortResponseDto;
  counterparty: CounterpartyResponseDto;
  agreement: AgreementResponseDto;
  currency: CurrencyResponseDto;
  priceType: PriceTypeResponseDto;
  sum: number;
  salesInvoiceGoods: SalesInvoiceGoodResponseDto[];
}
