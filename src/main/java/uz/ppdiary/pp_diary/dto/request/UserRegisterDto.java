package uz.ppdiary.pp_diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.util.annotation.ValidEmail;
import uz.ppdiary.pp_diary.util.annotation.ValidPassword;
import uz.ppdiary.pp_diary.util.annotation.ValidUsername;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @ValidUsername
    private String username;
    @ValidPassword
    private String password;
    @ValidEmail
    private String email;
    private String fullName;
}
