package ma.youcode.managment_tournoi_backend.rest.controller;

import io.micrometer.observation.Observation;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.groupDto.GroupShowDto;
import ma.youcode.managment_tournoi_backend.dto.groupTeamDto.GroupTeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.Group;
import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import ma.youcode.managment_tournoi_backend.mapper.GroupMapper;
import ma.youcode.managment_tournoi_backend.mapper.TeamGroupMapper;
import ma.youcode.managment_tournoi_backend.service.GroupService;
import ma.youcode.managment_tournoi_backend.service.TeamGroupService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teamGroup")
public class TeamGroupController {
    private final TeamGroupService teamGroupService;
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<List<GroupShowDto>> createTeamGroup() {
        teamGroupService.createGroupTeam();
        List<Group> allGroups = groupService.getAllGroups();
        List<GroupShowDto> listGroupMapped = allGroups.stream().map(GroupMapper.INSTANCE::groupToGroupShowDto).toList();
        return ResponseEntity.ok(listGroupMapped);

    }
    @GetMapping
    public ResponseEntity<List<GroupShowDto>> getTeamGroups() {
        List<Group> allGroups = groupService.getAllGroups();
        List<GroupShowDto> listGroupMapped = allGroups.stream().map(GroupMapper.INSTANCE::groupToGroupShowDto).toList();
        return ResponseEntity.ok(listGroupMapped);
    }
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAllGrp(){
        teamGroupService.deleteALLGroupTeam();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted all groups with success");
        return ResponseEntity.ok(response);
    }
}
