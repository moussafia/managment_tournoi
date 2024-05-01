package ma.youcode.managment_tournoi_backend.security.authDto;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorAuthDto {
    private int status;
    private String error;
    private Instant timestamp;
    private String message;
    private String path;
}
