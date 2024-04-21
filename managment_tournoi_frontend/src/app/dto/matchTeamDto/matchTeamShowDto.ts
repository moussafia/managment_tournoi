import { LevelEnum } from "../enum/levelEnum";
import { TeamShowDto } from "../teamDto/teamShowDto";

export interface MatchTeamShowDto {
    id: MatchTeamEmbeddedId;
    team: TeamShowDto;
    passed: boolean;
    win: boolean;
    draw: boolean;
    level: LevelEnum;
    result: number;
}
export interface MatchTeamEmbeddedId {
    matchId: string;
    teamId: string;
}