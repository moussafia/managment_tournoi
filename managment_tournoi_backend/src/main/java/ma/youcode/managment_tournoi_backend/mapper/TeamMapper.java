package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamCreateDto;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamUpdateDto;
import ma.youcode.managment_tournoi_backend.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    Team TeamCreateDtoToTeam(TeamCreateDto teamCreateDto);
    Team TeamUpdateDtoToTeam(TeamUpdateDto teamUpdateDto);
    TeamShowDto TeamToTeamShowDto(Team team);
}
