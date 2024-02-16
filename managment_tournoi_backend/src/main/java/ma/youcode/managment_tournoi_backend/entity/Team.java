package ma.youcode.managment_tournoi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name_team;
    private String logo;
    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Participant> participants;
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Team_Group> teamGroups;
}
