import {ResponseDto} from "../response-dto";

export interface SalesIncomeResponseDto extends ResponseDto {

  date: Date;
  revenue: number;
  profit: number;
}
