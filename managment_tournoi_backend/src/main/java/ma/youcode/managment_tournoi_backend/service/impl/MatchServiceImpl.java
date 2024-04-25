package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.MatchRepository;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.service.MatchService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final AppUserService appUserService;

    @Override
    public Match createMatch(Match match, UUID userId) {
        validateDateMatch(match);
        validateIfDateAlreadyExist(match);
        AppUser arbitraire = appUserService.findMemberById(userId);
        match.setArbitrator(arbitraire);
        return matchRepository.save(match);
    }

    @Override
    public Match updateMatch(Match match, UUID userId) {
        Match matchExisted = getMatchById(match.getCodeMatch());
        validateDateMatch(match);
        validateIfDateAlreadyExist(match);
        AppUser arbitraire = appUserService.findMemberById(userId);
        matchExisted.setArbitrator(arbitraire);
        matchExisted.setEndDateMatch(match.getEndDateMatch());
        matchExisted.setStartDateMatch(matchExisted.getStartDateMatch());
        matchExisted.setDate(match.getDate());
        return matchRepository.save(match);
    }

    @Override
    public Match getMatchById(UUID matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));
    }

    @Override
    public Match getMatchToday() {
        return matchRepository.findByDate(LocalDate.now()).orElseThrow(() -> new EntityNotFoundException("No match for Today"));
    }


    private void validateDateMatch(Match match) {
        if (match.getDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Invalid date, Match is before date of new");
        }
        if (match.getStartDateMatch().isAfter(match.getEndDateMatch())) {
            throw new RuntimeException("Invalid date, start time of match is after of end time");
        }
    }
    private void validateIfDateAlreadyExist(Match match) {
        matchRepository.getMatchByDateAndStartTimeOrEndTime(match.getDate(), match.getStartDateMatch())
                .ifPresent(existingMatch -> {
                    throw new RuntimeException("A match already exists for date " + match.getDate()
                            + " and time " + match.getStartDateMatch());
                });
    }
}
