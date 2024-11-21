package uz.ppdiary.pp_diary.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.ppdiary.pp_diary.dto.response.AttachmentDto;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.enums.AttachmentStatus;
import uz.ppdiary.pp_diary.exceptions.NotFoundException;
import uz.ppdiary.pp_diary.repository.AttachmentRepository;
import uz.ppdiary.pp_diary.service.AttachmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static uz.ppdiary.pp_diary.utils.AppConst.BASE_PATH;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public AttachmentDto upload(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();

        String fileName = UUID.randomUUID() + getExtension(file.getContentType());

        Path path = BASE_PATH.resolve(fileName);

        Attachment attachment = Attachment.builder()
                .originalName(originalFileName)
                .name(fileName)
                .type(file.getContentType())
                .size(file.getSize())
                .path(path.toString())
                .build();

        Files.createDirectories(BASE_PATH);

        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }

        Attachment savedAttachment = attachmentRepository.save(attachment);

        return new AttachmentDto(savedAttachment);
    }

    @Override
    public AttachmentDto getById(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attachment"));

        return new AttachmentDto(attachment);
    }

    @Override
    public List<AttachmentDto> getAllByStatus(AttachmentStatus status) {
        return attachmentRepository.findAllByStatus(status)
                .stream()
                .map(AttachmentDto::new)
                .toList();
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attachment"));

        Path path = BASE_PATH.resolve(attachment.getName());

        Files.deleteIfExists(path);

        attachment.setStatus(AttachmentStatus.DELETED);
    }

    private String getExtension(String contentType) {
        if (contentType != null) {
            return "." + contentType.substring(contentType.indexOf("/") + 1);
        }
        return ".jpg";
    }
}
