import { ResponseDto } from "../response-dto";
import {CompanyResponseDto} from "../company/company-response-dto";
import {CounterpartyResponseDto} from "../counterparty/counterparty-response-dto";

export interface AgreementResponseDto extends ResponseDto {
  name: string;
  agreementType: string;
  company: CompanyResponseDto;
  counterparty: CounterpartyResponseDto;
}
