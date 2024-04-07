package ma.youcode.managment_tournoi_backend.util.xlsx;


import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.exception.XLSXFieldException;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.util.member.MemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
@Component
public class XLSXFieldValidation {
    private static AppUserRepository appUserRepository;

    public XLSXFieldValidation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public  static void  xlsxFieldValidationPattern(List<AppUser> appUserList){
        int index =  2;
        HashMap<Integer, List<String>> validationFieldMaps = new HashMap<>();
        for (AppUser appUser: appUserList){
            List<String> fieldsName = MemberUtils.validateMemberFieldNotMatchPattern(appUser);
            if(!fieldsName.isEmpty()){
                validationFieldMaps.put(index, fieldsName);
            }
            index++;
        }
        if(!validationFieldMaps.isEmpty())
            throw new XLSXFieldException("These field(s) is/are not valid. Please correct it/them.", validationFieldMaps);
    }
    public static void xlsxFieldValidationEmails(List<AppUser> appUserList){
        int index =  2;
        HashMap<Integer, List<String>> validationFieldMaps = new HashMap<>();
        for (AppUser user : appUserList){
            int finalIndex = index;
            appUserRepository.findByEmail(user.getEmail()).ifPresent( (user1) -> {
                validationFieldMaps.put(finalIndex, Collections.singletonList(user1.getEmail()));
            });
            index ++;
        }
        if(!validationFieldMaps.isEmpty())
            throw new XLSXFieldException("These email(s) already exist", validationFieldMaps);
    }
}
