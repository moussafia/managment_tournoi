package ma.youcode.managment_tournoi_backend.web.controller;

import ma.youcode.managment_tournoi_backend.util.ExcelUploadUtil;
import ma.youcode.managment_tournoi_backend.util.ExcelValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/member")
public class MemberController {
    @PostMapping("/add/members")
    public ResponseEntity<?> createListMembers(@RequestParam("memberFile") MultipartFile file){
        try {
            return ResponseEntity.ok(ExcelValidationUtil.readExcelFile(file.getInputStream()));
        }catch (IOException e){
            e.getStackTrace();
        }
        return null;
    }

}
