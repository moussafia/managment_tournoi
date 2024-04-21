import { AppRole } from "../../roleDto/AppRole";

export interface MemberShowDto {
    id: string; 
    firstName: string;
    lastName: string;
    username: string;
    email: string;
    urlPicture: string;
    className: string;
    createdAt: string;
    role: AppRole;
}
