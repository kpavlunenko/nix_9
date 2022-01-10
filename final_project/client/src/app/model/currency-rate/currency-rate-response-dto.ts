import {TableResponseDto} from "../table-response-dto";
import {CurrencyResponseDto} from "../currency/currency-response-dto";

export interface CurrencyRateResponseDto extends TableResponseDto {

  date: Date;
  currency: CurrencyResponseDto;
  rate: number;
  frequencyRate: number
}
