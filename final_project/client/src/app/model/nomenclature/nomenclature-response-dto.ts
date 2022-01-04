import {ResponseDto} from "../response-dto";
import {BusinessDirectionShortResponseDto} from "../business-direction/business -direction-short-response-dto";

export interface NomenclatureResponseDto extends ResponseDto {

  name: string;
  product: boolean;
  service: boolean;
  businessDirection: BusinessDirectionShortResponseDto;
}
