import {BankOperationType} from "./bank-operation-type";

export interface BankOperationRequestDto {

  amount: number;
  categoryId: number;
  bankAccountId: number;
  recipientBankAccountId: number;
  operationType: string;
  bankOperationType: BankOperationType;
}
