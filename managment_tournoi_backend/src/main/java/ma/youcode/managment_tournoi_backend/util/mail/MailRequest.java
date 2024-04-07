package ma.youcode.managment_tournoi_backend.util.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequest {
    public String name;
    public String to;
    public String subject;
}
