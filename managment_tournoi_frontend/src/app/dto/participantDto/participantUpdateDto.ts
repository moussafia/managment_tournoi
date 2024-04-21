import { TeamUpdateDto } from "../teamDto/teamUpdateDto";

export interface ParticipantUpdateDto {
    usersIds: string[];
    team: TeamUpdateDto;
    logo?: File;
    numberOfParticipants: number;
}