package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {
    @Query("SELECT m FROM Match m WHERE m.Date = :dateMatch AND (:startTime BETWEEN m.startDateMatch  AND m.endDateMatch)")
    Optional<Match> getMatchByDateAndStartTimeOrEndTime(@Param("dateMatch") LocalDate dateMatch, @Param("startTime") LocalTime startTime);

}
