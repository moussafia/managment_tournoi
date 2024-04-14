package ma.youcode.managment_tournoi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import ma.youcode.managment_tournoi_backend.entity.embedded.ParticipantEmbeddedId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant implements Serializable {
    @EmbeddedId
    private ParticipantEmbeddedId participantEmbeddedId = new ParticipantEmbeddedId();
    private LocalDateTime dateOfCreation;
    @MapsId("teamId")
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AppUser user;
}
