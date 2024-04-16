package ma.youcode.managment_tournoi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.managment_tournoi_backend.entity.embedded.MatchTeamEmbeddedId;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;

@Entity
@Getter
@Setter
public class MatchTeam {
    @EmbeddedId
    private MatchTeamEmbeddedId id;
    @MapsId("matchId")
    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private Match match;
    @MapsId("teamId")
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
    private boolean isPassed;
    private boolean isWin;
    private boolean isDraw;
    private LevelEnum level;
    private int result;
}
