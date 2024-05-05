import { AppRole } from "src/app/dto/roleDto/AppRole";

export interface AuthenticationResponseDto {
    id: string;
    firstName: string;
    lastName: string;
    username: string;
    email: string;
    role: AppRole;
    accessToken: string;
    refreshToken: string;
}