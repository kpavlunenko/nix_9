import {TableResponseDto} from "../table-response-dto";
import {NomenclatureResponseDto} from "../nomenclature/nomenclature-response-dto";
import {PriceTypeResponseDto} from "../price-type/price-type-response-dto";

export interface PriceResponseDto extends TableResponseDto {

  date: Date;
  priceType: PriceTypeResponseDto;
  nomenclature: NomenclatureResponseDto
  price: number;
}
