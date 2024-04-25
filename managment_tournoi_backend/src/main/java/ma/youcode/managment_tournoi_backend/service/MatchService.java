package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Match;

import java.util.UUID;

public interface MatchService {
    Match createMatch(Match match, UUID userId) ;
    Match updateMatch(Match match, UUID userId);
    Match getMatchById(UUID matchId);
    Match getMatchToday();
}
