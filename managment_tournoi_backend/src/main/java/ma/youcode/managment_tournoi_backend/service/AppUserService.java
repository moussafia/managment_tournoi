package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppUserService {
    boolean createListMembers(List<AppUser> members);
    Page<AppUser> getAllMembers();
}
