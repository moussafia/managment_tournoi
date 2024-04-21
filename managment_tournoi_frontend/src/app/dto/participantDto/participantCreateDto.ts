import { TeamCreateDto } from "../teamDto/teamCreateDto";

export interface ParticipantCreateDto {
    usersIds: string[];
    team: TeamCreateDto;
    logo: File;
    numberOfParticipants: number;
}