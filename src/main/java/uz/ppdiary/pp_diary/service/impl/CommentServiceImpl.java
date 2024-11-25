package uz.ppdiary.pp_diary.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.CommentCRUDDto;
import uz.ppdiary.pp_diary.dto.request.CommentUpdateDto;
import uz.ppdiary.pp_diary.dto.response.CommentDto;
import uz.ppdiary.pp_diary.entity.Comment;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;
import uz.ppdiary.pp_diary.exceptions.NotFoundException;
import uz.ppdiary.pp_diary.repository.CommentRepository;
import uz.ppdiary.pp_diary.repository.UserRepository;
import uz.ppdiary.pp_diary.service.CommentService;
import uz.ppdiary.pp_diary.service.ReactionService;
import uz.ppdiary.pp_diary.utils.SecurityUtils;
import uz.ppdiary.pp_diary.utils.Validations;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReactionService reactionService;

    @Override
    public CommentDto save(CommentCRUDDto commentCRUDDto) {
        User author = userRepository.findById(commentCRUDDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("User"));

        Comment comment = Comment.builder()
                .text(commentCRUDDto.getText())
                .author(author)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return new CommentDto(savedComment, Map.of(), null);
    }

    @Override
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));

        return mapToDtoWithReactions(comment);
    }

    @Override
    public Page<CommentDto> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(this::mapToDtoWithReactions);
    }

    @Override
    public Page<CommentDto> getAllByAuthorId(Long authorId, Pageable pageable) {
        return commentRepository.findAllByAuthorId(authorId, pageable)
                .map(this::mapToDtoWithReactions);
    }

    @Override
    public Page<CommentDto> getAllByTextContainingIgnoreCase(String text, Pageable pageable) {
        return commentRepository.findAllByTextContainingIgnoreCase(text, pageable)
                .map(this::mapToDtoWithReactions);
    }

    @Override
    public CommentDto update(CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        comment.setText(Validations.requireNonNullElse(commentUpdateDto.getText(), comment.getText()));
        return mapToDtoWithReactions(commentRepository.save(comment));
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }

    @Override
    public void deleteAllByAuthorId(Long authorId) {
        commentRepository.deleteAllByAuthorId(authorId);
    }

    private CommentDto mapToDtoWithReactions(Comment comment) {
        Map<ReactionType, Long> reactionCounts = reactionService.countReactionsByType(comment.getId());
        ReactionType userReaction = reactionService.getUserReaction(comment.getId(), SecurityUtils.getCurrentUserId());

        return new CommentDto(comment, reactionCounts, userReaction);
    }

}

