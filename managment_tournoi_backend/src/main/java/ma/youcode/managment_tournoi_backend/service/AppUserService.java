package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppUserService {
    boolean createListMembers(List<AppUser> members);
    AppUser createMember(AppUser member);
    AppUser findMemberById(UUID id);
    AppUser assignRoleToMember(UUID memberId, String roleName);
    AppUser updateMemberProfile(AppUser member);
    AppUser updatePassword(UUID memberId, String oldPassword, String newPassword);
    Page<AppUser> getAllMembers(Pageable pageable);
}
