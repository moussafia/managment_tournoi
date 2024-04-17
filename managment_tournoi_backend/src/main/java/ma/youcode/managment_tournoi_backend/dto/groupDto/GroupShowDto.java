package ma.youcode.managment_tournoi_backend.dto.groupDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.groupTeamDto.GroupTeamShowDto;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupShowDto {
    private Long id;
    private String name;
    private List<GroupTeamShowDto> teamGroups;
}
