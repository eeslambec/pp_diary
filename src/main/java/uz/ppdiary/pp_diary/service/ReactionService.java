package uz.ppdiary.pp_diary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.response.UserReactionsDto;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;

import java.util.Map;

@Service
public interface ReactionService {
    void addReactionToComment(Long commentId, ReactionType reactionType, Long userId);

    void removeReactionFromComment(Long commentId, ReactionType reactionType, Long userId);

    ReactionType getUserReaction(Long commentId, Long userId);

    Map<ReactionType, Long> countReactionsByType(Long commentId);

    Page<UserReactionsDto> findAllReactionsByUser(Long userId, Pageable pageable);
}
