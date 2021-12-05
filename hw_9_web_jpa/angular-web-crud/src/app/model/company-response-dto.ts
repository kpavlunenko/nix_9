import { ResponseDto } from "./response-dto";

export interface CompanyResponseDto extends ResponseDto {
  name: string;
  companyType: string;
}
