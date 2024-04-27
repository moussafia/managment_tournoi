import { TeamUpdateDto } from "../teamDto/teamUpdateDto";

export interface ParticipantUpdateDto {
    usersIds: string[];
    team: TeamUpdateDto;
    logo?: any;
    numberOfParticipants: number;
}