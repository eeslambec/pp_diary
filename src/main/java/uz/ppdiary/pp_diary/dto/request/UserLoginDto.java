package uz.ppdiary.pp_diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.utils.annotation.ValidPassword;

@Getter
@AllArgsConstructor
public class UserLoginDto {
    @NotBlank
    private String data;
    @ValidPassword
    private String password;
}
