package ma.youcode.managment_tournoi_backend.dto.matchDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.getDto.MemberShowDto;
import ma.youcode.managment_tournoi_backend.dto.matchTeamDto.MatchTeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchShowDto {
    private UUID codeMatch;
    private LocalDate date;
    private LocalTime startDateMatch;
    private LocalTime endDateMatch;
    private List<MatchTeamShowDto> matchTeams;
    private MemberShowDto arbitrator;
}
