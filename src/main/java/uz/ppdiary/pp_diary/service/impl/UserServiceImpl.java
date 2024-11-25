package uz.ppdiary.pp_diary.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.UserLoginDto;
import uz.ppdiary.pp_diary.dto.request.UserRegisterDto;
import uz.ppdiary.pp_diary.dto.response.JwtDto;
import uz.ppdiary.pp_diary.dto.response.SuccessResponse;
import uz.ppdiary.pp_diary.dto.response.UserDto;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.UserStatus;
import uz.ppdiary.pp_diary.exceptions.AlreadyExistsException;
import uz.ppdiary.pp_diary.exceptions.InvalidDataException;
import uz.ppdiary.pp_diary.exceptions.NotFoundException;
import uz.ppdiary.pp_diary.repository.UserRepository;
import uz.ppdiary.pp_diary.security.jwt.JwtTokenProvider;
import uz.ppdiary.pp_diary.service.UserService;
import uz.ppdiary.pp_diary.utils.annotation.ValidEmail;
import uz.ppdiary.pp_diary.utils.annotation.ValidPassword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private static final Map<String, User> TEMP_USERS = new HashMap<>();

    @Override
    public SuccessResponse register(@NotNull UserRegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent())
            throw new AlreadyExistsException("User");

        if (userRepository.findByUsername(dto.getUsername()).isPresent())
            throw new AlreadyExistsException("User");

        emailService.send(dto.getEmail());

        TEMP_USERS.put(dto.getEmail(), User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .status(UserStatus.NOT_VERIFIED)
                .build());

        return new SuccessResponse("Email sent");
    }

    @Override
    public JwtDto checkEmail(@ValidEmail String email, @NotNull Integer code) {
        if (emailService.check(code, email)) {
            User user = TEMP_USERS.get(email);
            if (user == null)
                throw new NotFoundException("User");
            user.setStatus(UserStatus.VERIFIED);
            return new JwtDto(jwtTokenProvider.generateToken(userRepository.save(user)));
        }
        throw new InvalidDataException("verification code");
    }

    @Override
    public JwtDto login(@NotNull UserLoginDto dto) {
        User user;
        if (dto.getData().contains("@"))
            user = userRepository.findByEmail(dto.getData())
                    .orElseThrow(() -> new NotFoundException("User"));
        else
            user = userRepository.findByUsername(dto.getData())
                    .orElseThrow(() -> new NotFoundException("User"));

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword()))
            return new JwtDto(jwtTokenProvider.generateToken(user));
        throw new InvalidDataException("Password");
    }

    @Override
    public UserDto getById(@NotNull Long id) {
        return new UserDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .toList();
    }

    @Override
    public List<UserDto> getAllByStatus(@NotNull UserStatus status) {
        return userRepository.findAllByStatus(status)
                .stream()
                .map(UserDto::new)
                .toList();
    }

    @Override
    public SuccessResponse forgotPassword(@ValidEmail String email) {
        userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User"));

        emailService.send(email);
        return new SuccessResponse("Email sent");
    }

    @Override
    public SuccessResponse resetPassword(@ValidEmail String email, @NotNull Integer code, @ValidPassword String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User"));

        if (emailService.check(code, email)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return new SuccessResponse("Password reset successfully");
        } else
            throw new InvalidDataException("code");
    }
}
