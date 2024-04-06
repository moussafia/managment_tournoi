package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.AppUserRequest;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.mapper.AppUserMapper;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.util.xlsx.ExcelUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final AppUserService userService;
    @PostMapping("/add/members")
    public ResponseEntity<?> createListMembers(@RequestParam("memberFile") MultipartFile file){
        try {
            List<AppUserRequest> appUserRequestList = ExcelUploadUtil.readExcelFile(file.getInputStream());
            List<AppUser> appUserList = new ArrayList<>();
            for(AppUserRequest userDto: appUserRequestList){
                 appUserList.add(AppUserMapper.INSTANCE.AppUserFileDtoToAppUser(userDto));
            }
            userService.createListMembers(appUserList);
            return ResponseEntity.ok(ExcelUploadUtil.readExcelFile(file.getInputStream()));
        }catch (IOException e){
            e.getStackTrace();
        }
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
