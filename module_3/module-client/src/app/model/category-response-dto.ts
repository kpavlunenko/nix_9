import { ResponseDto } from "./response-dto";
import {BankOperationType} from "./bank-operation-type";

export interface CategoryResponseDto extends ResponseDto {
  name: string;
  bankOperationType: BankOperationType;
}
