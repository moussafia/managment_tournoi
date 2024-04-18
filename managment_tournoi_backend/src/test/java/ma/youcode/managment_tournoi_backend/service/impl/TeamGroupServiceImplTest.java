package ma.youcode.managment_tournoi_backend.service.impl;

import ma.youcode.managment_tournoi_backend.entity.Group;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import ma.youcode.managment_tournoi_backend.entity.enums.GroupEnum;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.TeamGroupRepository;
import ma.youcode.managment_tournoi_backend.service.GroupService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamGroupServiceImplTest {
    @Mock
    private TeamGroupRepository teamGroupRepository;
    @Mock
    private TeamService teamService;
    @Mock
    private GroupService groupService;
    @InjectMocks
    private TeamGroupServiceImpl teamGroupService;

    @Test
    void createGroupTeam() {
        when(teamService.getLatestCreatedTeam()).thenReturn(generateFakeTeamList());
        Group groupSaved = new Group(2L, "A", null);
        when(groupService.addGroup(any(Group.class))).thenReturn(groupSaved);
        teamGroupService.createGroupTeam();
        verify(teamService).getLatestCreatedTeam();
        verify(groupService, times(2)).addGroup(any(Group.class));
        verify(teamGroupRepository, times(2)).saveAll(anyList());
    }
    private List<Team> generateFakeTeamList() {
        List<Team> fakeTeamList = new ArrayList<>();
        fakeTeamList.add(new Team());
        fakeTeamList.add(new Team());
        fakeTeamList.add(new Team());
        fakeTeamList.add(new Team());
        fakeTeamList.add(new Team());
        fakeTeamList.add(new Team());
        fakeTeamList.add(new Team());
        return fakeTeamList;
    }

    @Test
    void getGroupTeamById(){
        Long groupId = 1L;
        Group groupSaved = new Group(1L, "A", null);
        TeamGroup expectedGroup = new TeamGroup(groupId, false,0,0,0,0,1000,null ,groupSaved);
        when(teamGroupRepository.findById(groupId)).thenReturn(Optional.of(expectedGroup));
        TeamGroup result = teamGroupService.getGroupTeamById(groupId);
        assertEquals(expectedGroup, result);
    }

    @Test
    void getGroupTeamById_EntityNotFoundException() {
        Long groupId = 1L;
        when(teamGroupRepository.findById(groupId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            teamGroupService.getGroupTeamById(groupId);
        });
    }
}