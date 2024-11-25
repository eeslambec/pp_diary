package uz.ppdiary.pp_diary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ppdiary.pp_diary.entity.Comment;
import org.springframework.data.domain.Pageable;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"reactions"})
    Page<Comment> findAllByAuthorId(Long authorId, Pageable pageable);

    @EntityGraph(attributePaths = {"reactions"})
    Page<Comment> findAllByTextContainingIgnoreCase(String text, Pageable pageable);

    void deleteAllByAuthorId(Long authorId);
}
