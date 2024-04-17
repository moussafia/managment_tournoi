package ma.youcode.managment_tournoi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codeMatch;
    private LocalDate date;
    private LocalTime startDateMatch;
    private LocalTime endDateMatch;
    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MatchTeam> matchTeams;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser arbitrator;
}
