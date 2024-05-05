import { AppRole } from "src/app/dto/roleDto/AppRole";

export interface AuthenticationResponseDto {
    id: string;
    firstName: string;
    lastName: string;
    username: string;
    email: string;
    role: AppRole;
    access_token: string;
    refresh_token: string;
}