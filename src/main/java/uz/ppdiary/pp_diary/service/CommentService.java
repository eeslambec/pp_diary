package uz.ppdiary.pp_diary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.CommentCRUDDto;
import uz.ppdiary.pp_diary.dto.request.CommentUpdateDto;
import uz.ppdiary.pp_diary.dto.response.CommentDto;

@Service
public interface CommentService {
    CommentDto save(CommentCRUDDto commentCRUDDto);
    CommentDto getById(Long id);
    Page<CommentDto> getAll(Pageable pageable);
    Page<CommentDto> getAllByAuthorId(Long id, Pageable pageable);
    Page<CommentDto> getAllByTextContainingIgnoreCase(String text, Pageable pageable);
    CommentDto update (CommentUpdateDto commentUpdateDto);
    void deleteById(Long id);
    void deleteAllByAuthorId(Long authorId);
}
