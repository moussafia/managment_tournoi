package ma.youcode.managment_tournoi_backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppRole {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Enumerated(EnumType.STRING)
private RoleEnum name;
@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
@JsonBackReference
private List<AppUser> users;

}
