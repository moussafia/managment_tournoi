package ma.youcode.managment_tournoi_backend.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
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
public class ParticipantEmbeddedId implements Serializable {
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "team_id")
    private UUID teamId;
}
