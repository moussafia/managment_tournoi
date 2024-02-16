package ma.youcode.managment_tournoi_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team_Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isPassed;
    private Integer points;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Group group;
}
