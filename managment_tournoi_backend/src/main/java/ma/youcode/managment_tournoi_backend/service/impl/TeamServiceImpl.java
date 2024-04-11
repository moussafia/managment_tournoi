package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.exception.EntityAlreadyExistException;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.TeamRepository;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import ma.youcode.managment_tournoi_backend.util.image.ImageUtils;
import ma.youcode.managment_tournoi_backend.util.team.TeamUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(UUID id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team with ID " + id + " not found"));
    }

    @Override
    public Team createTeam(Team team, MultipartFile image) {
        TeamUtils.validateIfNameOfTeamAlreadyExistsForCreate(team);
        ImageUtils imageUtil = new ImageUtils();
        imageUtil.validateImage(image);
        ImageUtils.ImageUploadResult imageUploadResult = imageUtil.saveImageToCloudinary(image, "logo_team");
        team.setLogo(imageUploadResult.getUrl());
        team.setLogoPublicId(imageUploadResult.getPublic_id());
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team, MultipartFile image) {
        TeamUtils.validateIfNameOfTeamAlreadyExistsForUpdate(team);
        getTeamById(team.getId());
        ImageUtils imageUtil = new ImageUtils();
        imageUtil.validateImage(image);
        ImageUtils.ImageUploadResult imageUploadResult = imageUtil.saveImageToCloudinary(image, "logo_team");
        team.setLogo(imageUploadResult.getUrl());
        team.setLogoPublicId(imageUploadResult.getPublic_id());
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(UUID teamId) {
        Team team = getTeamById(teamId);
         teamRepository.delete(team);
    }
}
