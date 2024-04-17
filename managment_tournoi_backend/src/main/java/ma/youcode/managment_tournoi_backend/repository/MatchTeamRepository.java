package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.embedded.MatchTeamEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MatchTeamRepository extends JpaRepository<MatchTeam, MatchTeamEmbeddedId> {
    void deleteAllByMatch_CodeMatch(UUID codeMatch);
    List<MatchTeam> findAllByTeam_Id(UUID id);
    List<MatchTeam> findAllByMatch_CodeMatch(UUID codeMatch);
}
