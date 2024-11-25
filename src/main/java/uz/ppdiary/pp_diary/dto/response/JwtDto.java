package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JwtDto {
    private String token;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
