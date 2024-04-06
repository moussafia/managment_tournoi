package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.util.xlsx.XLSXFieldValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    @Override
    public List<AppUser> createListMembers(List<AppUser> members) {
        XLSXFieldValidation.xlsxFieldValidationPattern(members);
        XLSXFieldValidation.xlsxFieldValidationEmails(members);
        return userRepository.saveAll(members);
    }

    @Override
    public List<AppUser> getAllMembers() {
        return null;
    }
}
