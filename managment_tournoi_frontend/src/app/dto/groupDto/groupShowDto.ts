import { GroupTeamShowDto } from "../groupTeamDto/groupTeamShowDto";

export interface GroupShowDto {
    id: number;
    name: string;
    teamGroups: GroupTeamShowDto[];
}
