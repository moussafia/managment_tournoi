package ma.youcode.managment_tournoi_backend.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.createMemberDto.AppUserRequest;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.createMemberDto.MemberSaveResponseDto;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.getDto.MemberShowDto;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.updateMemberDto.PasswordRequestUpdateDto;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.updateMemberDto.UpdateMemberDto;
import ma.youcode.managment_tournoi_backend.dto.roleDto.AssignRoleDto;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.mapper.AppUserMapper;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.util.xlsx.ExcelUploadUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final AppUserService userService;

    @PostMapping("/add/members")
    public ResponseEntity<MemberSaveResponseDto> createListMembers(@RequestParam("memberFile") MultipartFile file){
        try {
            ExcelUploadUtil.isValidExcelFile(file);
            List<AppUserRequest> appUserRequestList = ExcelUploadUtil.readExcelFile(file.getInputStream());
            List<AppUser> appUserList = new ArrayList<>();
            for(AppUserRequest userDto: appUserRequestList){
                 appUserList.add(AppUserMapper.INSTANCE.AppUserFileDtoToAppUser(userDto));
            }
            boolean isMembersInserted = userService.createListMembers(appUserList);
            if(isMembersInserted){
                return ResponseEntity.ok(
                        new MemberSaveResponseDto().builder()
                                .message("members are successfully inserted")
                                .isInserted(isMembersInserted).build()
                );
            }else {
                return ResponseEntity.ok(
                        new MemberSaveResponseDto().builder()
                                .message("Failed to insert members")
                                .isInserted(isMembersInserted).build()
                );
            }
        }catch (IOException e){
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MemberSaveResponseDto()
                            .builder()
                            .message("Failed to read Excel file, make sure it is in xlsx format")
                            .isInserted(false)
                            .build());
        }
    }

    @PostMapping("add/member")
    public ResponseEntity<MemberSaveResponseDto> createMember(@Valid @RequestBody AppUserRequest appUserRequest){
        AppUser member = AppUserMapper.INSTANCE.AppUserFileDtoToAppUser(appUserRequest);
        userService.createMember(member);
        return ResponseEntity.ok(
                new MemberSaveResponseDto().builder()
                        .message("members is successfully inserted")
                        .isInserted(true).build()
        );
    }

    @PutMapping("update/profile")
    public ResponseEntity<MemberSaveResponseDto> updateMemberProfile(@Valid @RequestBody UpdateMemberDto memberDto){
        AppUser member = AppUserMapper.INSTANCE.AppUserFileDtoToAppUser(memberDto);
        userService.updateMemberProfile(member);
        return ResponseEntity.ok(
                new MemberSaveResponseDto().builder()
                        .message("member is successfully updated")
                        .isInserted(true).build()
        );
    }


    @PutMapping("update/password")
    public ResponseEntity<MemberSaveResponseDto> updateMemberPassword(@Valid @RequestBody PasswordRequestUpdateDto passwordDto){
        userService.updatePassword(passwordDto.getMemberId(), passwordDto.getOldPassword(), passwordDto.getNewPassword());
        return ResponseEntity.ok(
                new MemberSaveResponseDto().builder()
                        .message("member password is successfully updated")
                        .isInserted(true).build()
        );
    }

    @GetMapping
   @PreAuthorize("hasAnyAuthority('BDE', 'MEMBER')")
    public ResponseEntity<Page<MemberShowDto>> getMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> s = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println("yassine authority" + s.stream().map(a-> a.getAuthority()).collect(Collectors.joining(" ")));
        String sf = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("yassine name" + sf);
        Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
        System.out.println("Authentication: 2 " + auth.size());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<AppUser> allMembers = userService.getAllMembers(pageable);
        Page<MemberShowDto> membersDtos =  allMembers.map(AppUserMapper.INSTANCE::AppUserToAppUserDto);
        return ResponseEntity.ok(membersDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberShowDto> getMemberById(@PathVariable String id){
        UUID idMember = UUID.fromString(id);
        AppUser member = userService.findMemberById(idMember);
        return ResponseEntity.ok(AppUserMapper.INSTANCE.AppUserToAppUserDto(member));
    }
    @PutMapping("/assign/role")
    public ResponseEntity<MemberShowDto> assignRole(@Valid @RequestBody AssignRoleDto assignRoleDto){
        AppUser member = userService.assignRoleToMember(assignRoleDto.getMemberId(), assignRoleDto.getRoleName());
        return ResponseEntity.ok(AppUserMapper.INSTANCE.AppUserToAppUserDto(member));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<MemberShowDto>> searchUsers(@RequestParam("keyword") String keyword,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "createdAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<AppUser> appUserList = userService.searchUser(keyword, pageable);
        Page<MemberShowDto> membersDtos =  appUserList.map(AppUserMapper.INSTANCE::AppUserToAppUserDto);
        return ResponseEntity.ok(membersDtos);
    }


}
