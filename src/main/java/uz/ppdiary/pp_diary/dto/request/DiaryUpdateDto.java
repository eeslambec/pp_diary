package uz.ppdiary.pp_diary.dto.request;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.Comment;
import uz.ppdiary.pp_diary.entity.Reaction;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.DiaryStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryUpdateDto {
    private Long id;
    private String title;
    private String text;
    private List<Attachment> medias;
    private List<User> mentionedUsers;
    private LocalDate happenedDate;
    private DiaryStatus diaryStatus;
}
