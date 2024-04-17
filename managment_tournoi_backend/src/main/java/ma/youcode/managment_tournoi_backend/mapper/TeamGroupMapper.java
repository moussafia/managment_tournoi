package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.groupTeamDto.GroupTeamShowDto;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamGroupMapper {
    TeamGroupMapper INSTANCE = Mappers.getMapper(TeamGroupMapper.class);
    @Mapping(source = "isPassed", target = "isPassed")
    @Mapping(source = "points", target = "points")
    @Mapping(source = "wins", target = "wins")
    @Mapping(source = "draws", target = "draws")
    @Mapping(source = "losses", target = "losses")
    @Mapping(source = "rank", target = "rank")
    @Mapping(source = "team", target = "team", qualifiedByName = "mapTeam")
    GroupTeamShowDto toGroupTeamShowDto(TeamGroup teamGroup);

    @Named("mapTeam")
    default TeamShowDto mapTeam(Team team) {
        return TeamMapper.INSTANCE.TeamToTeamShowDto(team);
    }
}
