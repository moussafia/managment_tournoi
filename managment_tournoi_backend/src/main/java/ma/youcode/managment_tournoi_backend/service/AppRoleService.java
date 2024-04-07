package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;

import java.util.Optional;

public interface AppRoleService {
    AppRole getRoleByName(RoleEnum name);
}
