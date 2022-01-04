import {ResponseDto} from "../response-dto";

export interface UserResponseDto extends ResponseDto {

  email: string;
  enabled: boolean;
  roleType: string;
  birthDay: Date;
  firstName: string;
  lastName: string;
}
