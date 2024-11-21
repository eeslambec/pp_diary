package uz.ppdiary.pp_diary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.ppdiary.pp_diary.dto.response.AttachmentDto;
import uz.ppdiary.pp_diary.entity.enums.AttachmentStatus;

import java.io.IOException;
import java.util.List;

@Service
public interface AttachmentService {
    AttachmentDto upload(MultipartFile file) throws IOException;
    AttachmentDto getById(Long id);
    List<AttachmentDto> getAllByStatus(AttachmentStatus status);
    void deleteById(Long id) throws IOException;

}
