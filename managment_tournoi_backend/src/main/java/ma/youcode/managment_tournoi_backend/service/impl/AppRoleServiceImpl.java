package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.AppRoleRepository;
import ma.youcode.managment_tournoi_backend.service.AppRoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppRoleServiceImpl implements AppRoleService {
    private final AppRoleRepository appRoleRepository;
    @Override
    public AppRole getRoleByName(RoleEnum name) {
        return appRoleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("role with name " + name + " not found"));
    }
}
