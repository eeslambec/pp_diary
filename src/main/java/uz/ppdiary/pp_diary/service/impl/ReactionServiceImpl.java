package uz.ppdiary.pp_diary.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.response.CommentDto;
import uz.ppdiary.pp_diary.dto.response.ReactionCountDto;
import uz.ppdiary.pp_diary.dto.response.UserReactionsDto;
import uz.ppdiary.pp_diary.entity.Comment;
import uz.ppdiary.pp_diary.entity.CommentReaction;
import uz.ppdiary.pp_diary.entity.Reaction;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;
import uz.ppdiary.pp_diary.exceptions.NotFoundException;
import uz.ppdiary.pp_diary.repository.ReactionRepository;
import uz.ppdiary.pp_diary.repository.CommentRepository;
import uz.ppdiary.pp_diary.repository.UserRepository;
import uz.ppdiary.pp_diary.service.ReactionService;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    @Override
    public void addReactionToComment(Long commentId, ReactionType reactionType, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment"));
        User reactedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User"));

        CommentReaction commentReaction = reactionRepository
                .findByCommentIdAndReactionReactionTypeAndReactionReactedUserId(commentId, reactionType, userId)
                .orElseGet(() -> CommentReaction.builder()
                        .comment(comment)
                        .reaction(Reaction.builder()
                                .reactionType(reactionType)
                                .reactedUser(reactedUser)
                                .build())
                        .build());

        reactionRepository.save(commentReaction);
    }


    @Override
    public void removeReactionFromComment(Long commentId, ReactionType reactionType, Long userId) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment"));

        CommentReaction commentReaction = reactionRepository
                .findByCommentIdAndReactionReactionTypeAndReactionReactedUserId(commentId, reactionType, userId)
                .orElseThrow(() -> new NotFoundException("Reaction"));

        reactionRepository.delete(commentReaction);
    }

    @Override
    public ReactionType getUserReaction(Long commentId, Long userId) {
        Optional<CommentReaction> commentReaction = reactionRepository
                .findByCommentIdAndReactionReactedUserId(commentId, userId);

        return commentReaction.map(cr -> cr.getReaction().getReactionType())
                .orElse(null);  // Если реакции нет, возвращаем null
    }


    @Override
    public Map<ReactionType, Long> countReactionsByType(Long commentId) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment"));

        List<ReactionCountDto> reactionCounts = reactionRepository.countReactionsByCommentIdIfExists(commentId);

        return reactionCounts.stream()
                .collect(Collectors.toMap(
                        ReactionCountDto::getReactionType,
                        ReactionCountDto::getCount
                ));
    }

    @Override
    public Page<UserReactionsDto> findAllReactionsByUser(Long userId, Pageable pageable) {
        Page<Object[]> results = reactionRepository.findAllReactionsByUser(userId, pageable);

        return results.map(result -> {
            Comment comment = (Comment) result[0];
            ReactionType reactionType = (ReactionType) result[1];

            //TODO: add current user reaction after adding security and JWT
            CommentDto commentDto = new CommentDto(comment, Map.of(), null);

            return new UserReactionsDto(commentDto, reactionType);
        });
    }


}
