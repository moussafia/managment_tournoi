package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.enums.RoleEnum;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.service.AppRoleService;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.util.mail.MailUtil;
import ma.youcode.managment_tournoi_backend.util.member.MemberUtils;
import ma.youcode.managment_tournoi_backend.util.xlsx.XLSXFieldValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppRoleService appRoleService;
    private final MailUtil mailUtil;
    private final AppUserRepository appUserRepository;

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
           // mailUtil.sendMail(member, password);
        }
        List<AppUser> appUserList = userRepository.saveAll(members);
        return appUserList.size() == members.size();
    }

    @Override
    public AppUser createMember(AppUser member) {
        MemberUtils.validateMemberFieldPattern(member);
        MemberUtils.checkUserIfAlreadyExist(member.getEmail());
        String password = MemberUtils.generatePassword();
        String username = MemberUtils.generateUsername(member.getFirstName(), member.getLastName());
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setCreatedAt(LocalDateTime.now());
        member.setIsDeleted(Boolean.FALSE);
        AppRole roleMember = appRoleService.getRoleByName(RoleEnum.MEMBER);
        member.setRole(roleMember);
        //mailUtil.sendMail(member, password);
        return userRepository.save(member);
    }

    @Override
    public AppUser findMemberById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    @Override
    public AppUser assignRoleToMember(UUID memberId, String roleName) {
        AppUser appUser = findMemberById(memberId);
        AppRole appRole = appRoleService.getRoleByName(RoleEnum.valueOf(roleName.toUpperCase()));
        appUser.setRole(appRole);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateMemberProfile(AppUser member) {
        AppUser appUser = findMemberById(member.getId());
        AppRole appRole = appRoleService.getRoleByName(RoleEnum.MEMBER);
        appUser.setRole(appRole);
        appUser.setClassName(member.getClassName());
        appUser.setEmail(member.getEmail());
        appUser.setFirstName(member.getFirstName());
        appUser.setLastName(member.getLastName());
        appUser.setUrlPicture(member.getUrlPicture());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updatePassword(UUID memberId, String oldPassword, String newPassword) {
        AppUser member = userRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("user not found"));
        if(!passwordEncoder.matches(oldPassword, member.getPassword())) {
            throw new RuntimeException("Old password does not match");
        }
        member.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(member);
    }


    @Override
    public Page<AppUser> getAllMembers(Pageable pageable) {
        return appUserRepository.findAll(pageable);
    }

}
