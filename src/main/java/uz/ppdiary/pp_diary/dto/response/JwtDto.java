package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class JwtDto {
    private String token;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
