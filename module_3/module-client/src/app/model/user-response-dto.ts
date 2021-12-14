import { ResponseDto } from "./response-dto";
import {BankAccountShortResponseDto} from "./bank-account-short-response-dto";

export interface UserResponseDto extends ResponseDto {
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  bankAccounts: BankAccountShortResponseDto[];
}
