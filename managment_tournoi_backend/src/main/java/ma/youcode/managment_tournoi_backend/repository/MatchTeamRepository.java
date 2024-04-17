package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.embedded.MatchTeamEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatchTeamRepository extends JpaRepository<MatchTeam, MatchTeamEmbeddedId> {
    void deleteAllByMatch_matchId(UUID codeMatch);
    List<MatchTeam> findByMatch_teamId(UUID teamId);
    List<MatchTeam> findByMatch_matchId(UUID matchId);

}
