import { TeamCreateDto } from "../teamDto/teamCreateDto";

export interface ParticipantCreateDto {
    usersIds: string[];
    team: string;
    logo: any;
    numberOfParticipants: number;
}