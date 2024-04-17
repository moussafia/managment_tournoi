package ma.youcode.managment_tournoi_backend.dto.matchDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor @NoArgsConstructor
public class MatchCreateDto {
    private LocalDate Date;
    private LocalTime startDateMatch;
    private LocalTime endDateMatch;
}
