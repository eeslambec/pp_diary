package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;

@Getter
@AllArgsConstructor
public class UserReactionsDto {
    private CommentDto comment;
    private ReactionType reactionType;
}
