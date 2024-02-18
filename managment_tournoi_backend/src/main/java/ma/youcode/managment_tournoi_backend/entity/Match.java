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
    private UUID code_match;
    private LevelEnum level;
    private LocalDate Date;
    private LocalTime startDateMatch;
    private LocalTime endDateMatch;
    private String description;
    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<MatchPicture> pictures;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser arbitrator;
    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Scorer> scorers;
    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private List<Image> images;
}
