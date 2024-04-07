package ma.youcode.managment_tournoi_backend.util.xlsx;


import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.exception.XLSXFieldException;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
@Component
public class XLSXFieldValidation {
    private static AppUserRepository appUserRepository;
    private final static String firstNamePattern = "[a-zA-Z]+";
    private final static String lastNamePattern = "[a-zA-Z]+";
    private final static String emailPattern = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
    private final static String urlPattern = "^https://intranet.youcode.ma/storage/users/profile/[^/]+\\.JPG$";

    public XLSXFieldValidation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public  static void  xlsxFieldValidationPattern(List<AppUser> appUserList){
        int index =  2;
        HashMap<Integer, List<String>> validationFieldMaps = new HashMap<>();
        for (AppUser appUser: appUserList){
            List<String> fieldsName = new ArrayList<>();
            if(!appUser.getFirstName().matches(firstNamePattern) || appUser.getFirstName().isBlank() || appUser.getFirstName().isEmpty() || appUser.getFirstName() == null){
                fieldsName.add("First Name");
            }
            if(!appUser.getLastName().matches(lastNamePattern) || appUser.getLastName().isBlank() || appUser.getLastName().isEmpty() || appUser.getLastName() == null){
                fieldsName.add("Last Name");
            }
            if(!appUser.getUrlPicture().matches(urlPattern) || appUser.getUrlPicture().isBlank() || appUser.getUrlPicture().isEmpty() || appUser.getUrlPicture() == null){
                fieldsName.add("picture url");
            }
            if(!appUser.getEmail().matches(emailPattern) || appUser.getEmail().isBlank() || appUser.getEmail().isEmpty() || appUser.getEmail() == null){
                fieldsName.add("Email");
            }
            if(appUser.getClassName().isBlank() || appUser.getClassName().isEmpty() || appUser.getClassName() == null){
                fieldsName.add("Class name");
            }
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
