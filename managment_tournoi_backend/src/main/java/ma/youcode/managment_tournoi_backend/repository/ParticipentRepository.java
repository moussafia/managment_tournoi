package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParticipentRepository extends JpaRepository<Participant, UUID> {
    Optional<Participant> findByTeamAndUser(Team team, AppUser user);
}
