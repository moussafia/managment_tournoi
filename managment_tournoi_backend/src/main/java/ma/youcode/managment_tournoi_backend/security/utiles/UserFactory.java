package ma.youcode.managment_tournoi_backend.security.utiles;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;
import ma.youcode.managment_tournoi_backend.repository.AppRoleRepository;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUsers() {
        List<AppRole> roles = roleRepository.findAll();
        AppRole bde = roles.stream().filter(r -> r.getName().equals(RoleEnum.BDE)).findFirst().orElse(null);
        AppRole member = roles.stream().filter(r -> r.getName().equals(RoleEnum.MEMBER)).findFirst().orElse(null);
        AppRole admin = roles.stream().filter(r -> r.getName().equals(RoleEnum.ADMIN)).findFirst().orElse(null);
        AppRole delegate = roles.stream().filter(r -> r.getName().equals(RoleEnum.DELEGATE)).findFirst().orElse(null);
        List<AppUser> users = List.of(
                new AppUser().builder().className("NAMEK").firstName("bilal").lastName("moussafia").role(delegate).username("b.moussafia").email("bilal@gmail.com").password(passwordEncoder.encode("1234")).build(),
                new AppUser().builder().className("SHARP CODERS").firstName("badar").lastName("moussafia").role(bde).username("bd.moussafia").email("badar@gmail.com").password(passwordEncoder.encode("1234")).build(),
                new AppUser().builder().className("LA CASA DEL JS").firstName("mehdi").lastName("moussafia").role(member).username("m.moussafia").email("mehdi@gmail.com").password(passwordEncoder.encode("1234")).build(),
                new AppUser().builder().className("VAN DER LINDE").firstName("maria").lastName("moussafia").role(admin).username("mr.moussafia").email("maria@gmail.com").password(passwordEncoder.encode("1234")).build()
        );
        appUserRepository.saveAll(users);
    }

    private List<AppRole> getRoles() {
        List<AppRole> roles = roleRepository.findAll();
        return roles;
    }
}
