package uz.ppdiary.pp_diary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ppdiary.pp_diary.dto.response.ReactionCountDto;
import uz.ppdiary.pp_diary.entity.CommentReaction;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<CommentReaction, Long> {
    Optional<CommentReaction> findByCommentIdAndReactionReactionTypeAndReactionReactedUserId(
            Long commentId, ReactionType reactionType, Long reactedUserId);

    Optional<CommentReaction> findByCommentIdAndReactionReactedUserId(Long commentId, Long userId);

    @Query("SELECT cr.comment, cr.reaction.reactionType " +
            "FROM CommentReaction cr " +
            "WHERE cr.reaction.reactedUser.id = :userId")
    Page<Object[]> findAllReactionsByUser(@Param("userId") Long userId, Pageable pageable);


    @Query("SELECT new uz.ppdiary.pp_diary.dto.response.ReactionCountDto(cr.reaction.reactionType, COUNT(cr)) " +
            "FROM CommentReaction cr " +
            "WHERE cr.comment.id = :commentId " +
            "GROUP BY cr.reaction.reactionType")
    List<ReactionCountDto> countReactionsByCommentIdIfExists(@Param("commentId") Long commentId);

}
