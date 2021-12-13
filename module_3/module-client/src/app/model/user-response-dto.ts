import { ResponseDto } from "./response-dto";

export interface UserResponseDto extends ResponseDto {
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
}
