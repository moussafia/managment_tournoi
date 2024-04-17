package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamGroupRepository extends JpaRepository<TeamGroup, Long> {
    Optional<TeamGroup> findByTeam_Id(UUID id);
}
