import { TeamShowDto } from "../teamDto/teamShowDto";

export interface GroupTeamShowDto {
    id: number;
    isPassed: boolean;
    points: number;
    wins: number;
    draws: number;
    losses: number;
    rank: number;
    team: TeamShowDto;
}
