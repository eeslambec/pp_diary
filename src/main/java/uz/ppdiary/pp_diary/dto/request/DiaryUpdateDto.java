package uz.ppdiary.pp_diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.DiaryStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryUpdateDto {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private List<Attachment> medias;
    private List<User> mentionedUsers;
    private LocalDate happenedDate;
    @NotNull
    private DiaryStatus diaryStatus;
}
