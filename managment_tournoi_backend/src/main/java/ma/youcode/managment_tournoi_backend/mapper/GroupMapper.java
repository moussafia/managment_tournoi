package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.groupDto.GroupShowDto;
import ma.youcode.managment_tournoi_backend.dto.groupTeamDto.GroupTeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.Group;
import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);
    @Mapping(source = "teamGroups", target = "teamGroups", qualifiedByName = "mapTeamGroups")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    GroupShowDto groupToGroupShowDto(Group group);
    @Named("mapTeamGroups")
    default List<GroupTeamShowDto> mapTeamGroups(List<TeamGroup> teamGroups) {
        return teamGroups.stream()
                .map(TeamGroupMapper.INSTANCE::toGroupTeamShowDto)
                .collect(Collectors.toList());
    }

}
