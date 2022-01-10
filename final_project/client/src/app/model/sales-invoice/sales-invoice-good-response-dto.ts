import {TableResponseDto} from "../table-response-dto";
import {NomenclatureResponseDto} from "../nomenclature/nomenclature-response-dto";

export interface SalesInvoiceGoodResponseDto extends TableResponseDto {

  nomenclature: NomenclatureResponseDto;
  price: number;
  quantity: number;
  sum: number
}
