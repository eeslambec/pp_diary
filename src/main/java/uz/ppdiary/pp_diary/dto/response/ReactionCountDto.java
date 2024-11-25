package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;

@Getter
@AllArgsConstructor
public class ReactionCountDto {
    private ReactionType reactionType;
    private Long count;
}

