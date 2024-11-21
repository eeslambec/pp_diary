package uz.ppdiary.pp_diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.Diary;
import uz.ppdiary.pp_diary.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryDto {
    private String title;
    @NotBlank
    private String text;
    private List<Long> mediaIds;
    @NotBlank
    private String authorUsername;
    private List<String> mentionedUsersUsernames;
    @NotBlank
    private LocalDate happenedDate;
    public DiaryDto(Diary diary) {
        this.title = diary.getTitle();
        this.text = diary.getText();
        this.mediaIds = diary.getMedias().stream().map(Attachment::getId).toList();
        this.mentionedUsersUsernames = diary.getMentionedUsers().stream().map(User::getUsername).toList();
        this.happenedDate = diary.getHappenedDate();
        this.authorUsername = diary.getAuthor().getUsername();
    }
}
