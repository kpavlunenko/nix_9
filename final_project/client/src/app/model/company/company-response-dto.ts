import { ResponseDto } from "../response-dto";
import {BusinessDirectionShortResponseDto} from "../business-direction/business -direction-short-response-dto";

export interface CompanyResponseDto extends ResponseDto {
  name: string;
  companyType: string;
  businessDirections: BusinessDirectionShortResponseDto[];
}
