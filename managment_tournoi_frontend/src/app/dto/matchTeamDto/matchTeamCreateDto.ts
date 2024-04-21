import { MatchCreateDto } from "../matchDto/matchCreateDto";

export interface MatchTeamCreateDto {
    match: MatchCreateDto;
    teamId: string[];
    arbitraire: string;
    levelEnum: string;
}
