package ma.youcode.managment_tournoi_backend.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class ScorerEmbeddedId implements Serializable {
    @Column(name = "participant_id")
    private ParticipantEmbeddedId participantEmbeddedId;
    @Column(name = "match_id")
    private UUID matchId;
}
