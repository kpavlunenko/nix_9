import { ResponseDto } from "./response-dto";
import {BankAccountShortResponseDto} from "./bank-account-short-response-dto";
import {CategoryResponseDto} from "./category-response-dto";
import {BankOperationType} from "./bank-operation-type";

export interface BankOperationResponseDto extends ResponseDto {

  amount: number;
  category: CategoryResponseDto;
  bankAccount: BankAccountShortResponseDto;
  recipientBankAccount: BankAccountShortResponseDto;
  operationType: string;
  bankOperationType: BankOperationType;
}
