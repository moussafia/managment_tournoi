package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.TeamRepository;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import ma.youcode.managment_tournoi_backend.util.image.ImageUtils;
import ma.youcode.managment_tournoi_backend.util.team.TeamUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ImageUtils imageUtil;
    @Override
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public Team getTeamById(UUID id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team with ID " + id + " not found"));
    }

    @Override
    public Team createTeam(Team team, MultipartFile image) {
        TeamUtils.validateIfNameOfTeamAlreadyExistsForCreate(team);
        imageUtil.validateImage(image);
        ImageUtils.ImageUploadResult imageUploadResult = imageUtil.saveImageToCloudinary(image, "logo_team");
        team.setLogo(imageUploadResult.getUrl());
        team.setLogoPublicId(imageUploadResult.getPublic_id());
        team.setCreatedAt(LocalDateTime.now());
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team, MultipartFile image) {
        Team teamById = getTeamById(team.getId());
        if(image != null && !Objects.requireNonNull(image.getOriginalFilename()).isEmpty()){
            imageUtil.validateImage(image);
            imageUtil.deleteImage(team.getLogoPublicId());
            ImageUtils.ImageUploadResult imageUploadResult = imageUtil.saveImageToCloudinary(image, "logo_team");
            team.setLogo(imageUploadResult.getUrl());
            team.setLogoPublicId(imageUploadResult.getPublic_id());
        }
        team.setCreatedAt(teamById.getCreatedAt());
        team.setUpdatedAt(LocalDateTime.now());
        team.setTeamGroups(teamById.getTeamGroups());
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(UUID teamId) {
        Team team = getTeamById(teamId);
         teamRepository.delete(team);
    }

    @Override
    public List<Team> getLatestCreatedTeam() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime startOfYear = LocalDateTime.of(localDateTime.getYear(), 1, 1, 0, 0);
        LocalDateTime endOfYear = LocalDateTime.of(localDateTime.getYear(), 12, 31, 23, 59);
        return teamRepository.findByCreatedAtBetween(startOfYear, endOfYear)
                .orElseThrow(() -> new RuntimeException("Team could not be found for "  + localDateTime.getYear()));


    }
}
