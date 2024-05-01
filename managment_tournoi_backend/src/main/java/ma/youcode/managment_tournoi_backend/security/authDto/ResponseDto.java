package ma.youcode.managment_tournoi_backend.security.authDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto<T> {
    private String message;
    private T result;
}