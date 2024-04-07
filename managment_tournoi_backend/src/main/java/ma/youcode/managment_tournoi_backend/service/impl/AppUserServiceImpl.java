package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.util.mail.MailRequest;
import ma.youcode.managment_tournoi_backend.util.mail.MailUtil;
import ma.youcode.managment_tournoi_backend.util.member.MemberUtils;
import ma.youcode.managment_tournoi_backend.util.xlsx.XLSXFieldValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    @Override
    public List<AppUser> createListMembers(List<AppUser> members) {
        XLSXFieldValidation.xlsxFieldValidationPattern(members);
        XLSXFieldValidation.xlsxFieldValidationEmails(members);
        for (AppUser member : members) {
            String password = MemberUtils.generatePassword();
            String username = MemberUtils.generateUsername(member.getFirstName(), member.getLastName());
            member.setUsername(username);
            member.setPassword(MemberUtils.encodePassword(password));
            member.setCreatedAt(LocalDateTime.now());
            member.setIsDeleted(Boolean.FALSE);
            MailUtil.sendMail(member, password);
        }
        return userRepository.saveAll(members);
    }

    @Override
    public List<AppUser> getAllMembers() {
        return null;
    }
}
