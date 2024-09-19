package uz.ppdiary.pp_diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.util.annotation.ValidPassword;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    private String data;
    @ValidPassword
    private String password;
}
