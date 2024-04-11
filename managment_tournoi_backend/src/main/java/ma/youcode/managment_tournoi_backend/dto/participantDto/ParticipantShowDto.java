package ma.youcode.managment_tournoi_backend.dto.participantDto;

import lombok.Builder;
import lombok.Data;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.getDto.MemberShowDto;

import java.time.LocalDateTime;
@Data
@Builder
public class ParticipantShowDto {
    private LocalDateTime dateOfCreation;
    private MemberShowDto user;
}
