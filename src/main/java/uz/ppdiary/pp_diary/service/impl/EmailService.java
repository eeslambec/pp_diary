package uz.ppdiary.pp_diary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.utils.annotation.ValidEmail;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final Map<String,Integer> validationCodes = new HashMap<>();

    @SneakyThrows
    @Async
    public void send(@ValidEmail String email) {
        TimeUnit.SECONDS.sleep(5);
        var helper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        Integer code = codeGenerator();
        helper.setFrom("abduganiyev.ismoil001@gmail.com");
        helper.setTo(email);
        helper.setSubject("Email verification");
        helper.setText("Your verification code : " + code);
        validationCodes.put(email,code);
        javaMailSender.send(helper.getMimeMessage());
    }
    public boolean check(Integer code,String email){
        Integer c = validationCodes.get(email);
        return Objects.equals(c, code);
    }
    public Integer codeGenerator(){
        return new Random().nextInt(9000) + 1000;
    }
}
