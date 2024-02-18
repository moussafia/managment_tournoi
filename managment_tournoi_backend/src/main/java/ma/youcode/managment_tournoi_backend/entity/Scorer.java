package ma.youcode.managment_tournoi_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.managment_tournoi_backend.entity.embedded.ScorerEmbeddedId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scorer {
    @EmbeddedId
    private ScorerEmbeddedId scorerEmbeddedId;
    private Integer number_goals;
    private Integer number_red_card;
    private Integer number_yellow_card;
    @ManyToOne
    @MapsId("participantEmbeddedId")
    private Participant participant;
    @ManyToOne
    @MapsId("matchId")
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
}
