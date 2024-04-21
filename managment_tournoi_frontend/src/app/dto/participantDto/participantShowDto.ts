import { MemberShowDto } from "../appUserFileDto/getDto/memberShowDto";

export interface ParticipantShowDto {
    dateOfCreation: string;
    user: MemberShowDto;
}