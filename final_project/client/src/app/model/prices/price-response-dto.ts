import {TableResponseDto} from "../table-response-dto";
import {NomenclatureResponseDto} from "../nomenclature/nomenclature-response-dto";

export interface PriceResponseDto extends TableResponseDto {

  priceType: PriceResponseDto;
  nomenclature: NomenclatureResponseDto
  price: number;
}
