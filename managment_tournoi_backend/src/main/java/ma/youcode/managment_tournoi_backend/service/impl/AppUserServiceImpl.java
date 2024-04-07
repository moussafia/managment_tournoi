package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.service.AppRoleService;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.util.mail.MailRequest;
import ma.youcode.managment_tournoi_backend.util.mail.MailUtil;
import ma.youcode.managment_tournoi_backend.util.member.MemberUtils;
import ma.youcode.managment_tournoi_backend.util.xlsx.XLSXFieldValidation;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppRoleService appRoleService;
    private final MailUtil mailUtil;
    @Override
    public boolean createListMembers(List<AppUser> members) {
        XLSXFieldValidation.xlsxFieldValidationPattern(members);
        XLSXFieldValidation.xlsxFieldValidationEmails(members);
        AppRole roleMember = appRoleService.getRoleByName(RoleEnum.MEMBER);
        for (AppUser member : members) {
            String password = MemberUtils.generatePassword();
            String username = MemberUtils.generateUsername(member.getFirstName(), member.getLastName());
            member.setUsername(username);
            member.setPassword(passwordEncoder.encode(password));
            member.setCreatedAt(LocalDateTime.now());
            member.setIsDeleted(Boolean.FALSE);
            member.setRole(roleMember);
            mailUtil.sendMail(member, password);
        }
        List<AppUser> appUserList = userRepository.saveAll(members);
        return appUserList.size() == members.size();
    }

    @Override
    public Page<AppUser> getAllMembers() {
        return null;
    }
}
