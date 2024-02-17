package ma.youcode.managment_tournoi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String image_profile;
    private LocalDateTime created_at;
    private LocalDateTime deleted_at;
    private LocalDateTime updated_at;
    private Boolean isDeleted;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppRole role;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Participant> participants;

}
