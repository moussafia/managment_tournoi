package ma.youcode.managment_tournoi_backend.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
 @AllArgsConstructor
public class MatchTeamEmbeddedId {
    @Column(name = "match_id")
    private UUID matchId;
    @Column(name = "team_id")
    private UUID teamId;
}
