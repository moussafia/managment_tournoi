package ma.youcode.managment_tournoi_backend.dto.appUserFileDto.createMemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MemberSaveResponseDto {
    private String message;
    private Boolean isInserted;
}
