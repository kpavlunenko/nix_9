import {CompanyResponseDto} from "./company-response-dto";
import {CounterpartyResponseDto} from "./counterparty-response-dto";

export interface AgreementRequestDto {

  name: string;
  agreementType: string;
  companyId: number;
  counterpartyId: number;
}
