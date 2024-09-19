package uz.ppdiary.pp_diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.enums.AttachmentStatus;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByStatus(AttachmentStatus status);
}
