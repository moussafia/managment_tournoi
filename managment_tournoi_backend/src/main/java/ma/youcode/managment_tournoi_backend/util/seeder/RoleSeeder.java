package ma.youcode.managment_tournoi_backend.util.seeder;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;
import ma.youcode.managment_tournoi_backend.repository.AppRoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final AppRoleRepository appRoleRepository;
public List<AppRole> createRoles(){
    List<AppRole> appRoles = new ArrayList<>();
    for(RoleEnum roleEnum : RoleEnum.values()){
        AppRole appRole = new AppRole().builder().name(roleEnum).build();
        appRoles.add(appRole);
    }
    return appRoleRepository.saveAll(appRoles);
}
}
