package ma.youcode.managment_tournoi_backend.util.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {
    private String message;
    private Boolean status;
}
