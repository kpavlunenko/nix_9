import { ResponseDto } from "../response-dto";

export interface CounterpartyResponseDto extends ResponseDto {

  name: string;
  inn: string,
  counterpartyType: string;
  countOfAgreement: number;
}
