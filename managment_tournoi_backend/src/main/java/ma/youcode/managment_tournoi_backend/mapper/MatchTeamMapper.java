package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.matchTeamDto.MatchTeamShowDto;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchTeamMapper {
    MatchTeamMapper INSTANCE = Mappers.getMapper(MatchTeamMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "team", target = "team")
    //@Mapping(source = "isPassed", target = "isPassed")
 //   @Mapping(source = "isWin", target = "isWin")
  //  @Mapping(source = "isDraw", target = "isDraw")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "result", target = "result")
    //@Mapping(source = "team" , target = "team", qualifiedByName = "mapTeam")
    MatchTeamShowDto toMatchTeamShowDto(MatchTeam matchTeam);

}
