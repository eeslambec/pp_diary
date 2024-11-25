package uz.ppdiary.pp_diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateDto {
    @NotNull
    private Long id;
    @NotBlank
    private String text;
    @NotNull
    private Long authorId;
}
