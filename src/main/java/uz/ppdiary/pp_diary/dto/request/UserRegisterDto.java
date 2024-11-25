package uz.ppdiary.pp_diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.utils.annotation.ValidEmail;
import uz.ppdiary.pp_diary.utils.annotation.ValidPassword;
import uz.ppdiary.pp_diary.utils.annotation.ValidUsername;

@Getter
@AllArgsConstructor
public class UserRegisterDto {
    @ValidUsername
    private String username;
    @ValidPassword
    private String password;
    @ValidEmail
    private String email;
    @NotBlank
    private String fullName;
}
