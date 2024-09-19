package uz.ppdiary.pp_diary.service;

import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.UserLoginDto;
import uz.ppdiary.pp_diary.dto.request.UserRegisterDto;
import uz.ppdiary.pp_diary.dto.response.JwtDto;
import uz.ppdiary.pp_diary.dto.response.SuccessResponse;
import uz.ppdiary.pp_diary.dto.response.UserDto;
import uz.ppdiary.pp_diary.entity.enums.UserStatus;

import java.util.List;

@Service
public interface UserService {
    SuccessResponse register(UserRegisterDto dto);
    JwtDto checkEmail(String email,Integer code);
    JwtDto login(UserLoginDto dto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    List<UserDto> getAllByStatus(UserStatus status);
    SuccessResponse forgotPassword(String email);
    SuccessResponse resetPassword(String email, Integer code, String newPassword);
}
