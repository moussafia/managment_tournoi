package ma.youcode.managment_tournoi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import ma.youcode.managment_tournoi_backend.entity.embedded.ParticipantEmbeddedId;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
    @EmbeddedId
    private ParticipantEmbeddedId participantEmbeddedId;
    @MapsId("teamId")
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
    @OneToMany(mappedBy = "participant", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Scorer> scorerList;
}
