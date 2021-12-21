import { ResponseDto } from "./response-dto";
import {CompanyShortResponseDto} from "./company-short-response-dto";

export interface BusinessDirectionResponseDto extends ResponseDto {
  name: string;
  companies: CompanyShortResponseDto[];
}
