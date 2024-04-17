package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.getDto.MemberShowDto;
import ma.youcode.managment_tournoi_backend.dto.matchDto.MatchCreateDto;
import ma.youcode.managment_tournoi_backend.dto.matchDto.MatchShowDto;
import ma.youcode.managment_tournoi_backend.dto.matchTeamDto.MatchTeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);
    @Mapping(source = "startDateMatch" , target = "startDateMatch")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "endDateMatch", target = "endDateMatch" )
    @Mapping(target = "codeMatch" , ignore = true )
    @Mapping(target =  "matchTeams", ignore = true )
    @Mapping(target = "arbitrator" , ignore = true )
    Match MatchCreateDtoToMatch(MatchCreateDto matchCreateDto);
    @Mapping(source = "codeMatch", target = "codeMatch")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "startDateMatch", target = "startDateMatch")
    @Mapping(source = "endDateMatch", target = "endDateMatch")
    @Mapping(source = "matchTeams", target = "matchTeams", qualifiedByName = "mapMatchTeam")
    @Mapping(source = "arbitrator", target = "arbitrator", qualifiedByName = "mapToUser")
    MatchShowDto MatchToMatchShowDto(Match match);
    @Named("mapMatchTeam")
    default List<MatchTeamShowDto> mapToUserDto(List<MatchTeam> matchTeams) {
        return matchTeams.stream().map(MatchTeamMapper.INSTANCE::toMatchTeamShowDto).toList();
    }
    @Named("mapToUser")
    default MemberShowDto mapToUserDto(AppUser member) {
        return AppUserMapper.INSTANCE.AppUserToAppUserDto(member);
    }
}
