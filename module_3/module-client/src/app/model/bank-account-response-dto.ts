import { ResponseDto } from "./response-dto";
import {UserShortResponseDto} from "./user-short-response-dto";

export interface BankAccountResponseDto extends ResponseDto {
  name: string;
  iban: string;
  user: UserShortResponseDto;
}
