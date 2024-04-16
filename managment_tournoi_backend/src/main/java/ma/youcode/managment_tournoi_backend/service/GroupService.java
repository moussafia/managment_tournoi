package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
Group addGroup(Group group);
List<Group> getAllGroups();
Optional<Group> getGroupById(Long id);
void deleteAll();
}
