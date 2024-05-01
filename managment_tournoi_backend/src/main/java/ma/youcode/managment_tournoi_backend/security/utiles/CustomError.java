package ma.youcode.managment_tournoi_backend.security.utiles;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CustomError {

    private final String field;
    private final String message;
}
