package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.dto.appUserDto.AppUserRequest;
import ma.youcode.managment_tournoi_backend.entity.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> createListMembers(List<AppUser> members);
    List<AppUser> getAllMembers();
}
