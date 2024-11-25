package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Comment;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private Long authorId;
    private Map<ReactionType, Long> reactionCounts; // Количество реакций по типам
    private ReactionType userReaction; // Реакция текущего пользователя

    public CommentDto(Comment comment, Map<ReactionType, Long> reactionCounts, ReactionType userReaction) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.authorId = comment.getAuthor().getId();
        this.reactionCounts = reactionCounts;
        this.userReaction = userReaction;
    }
}

