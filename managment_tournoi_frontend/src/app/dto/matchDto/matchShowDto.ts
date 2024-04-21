import { MemberShowDto } from "../appUserFileDto/getDto/memberShowDto";
import { MatchTeamShowDto } from "../matchTeamDto/matchTeamShowDto";

export interface MatchShowDto {
    codeMatch: string;
    date: string;
    startDateMatch: string; 
    endDateMatch: string; 
    matchTeams: MatchTeamShowDto[];
    arbitrator: MemberShowDto;
}

