import { ParticipantShowDto } from "../participantDto/participantShowDto";

export interface TeamShowDto {
    id: string;
    nameTeam: string;
    logo: string;
    logoPublicId: string;
    participants: ParticipantShowDto[];
}

