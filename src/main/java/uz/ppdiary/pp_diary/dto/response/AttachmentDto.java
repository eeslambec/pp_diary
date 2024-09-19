package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.enums.AttachmentStatus;

@AllArgsConstructor
@Getter
public class AttachmentDto {
    private Long id;
    private String originalName;
    private String name;
    private String type;
    private Long size;
    private String path;
    private AttachmentStatus status;

    public AttachmentDto(Attachment attachment) {
        this.id = attachment.getId();
        this.originalName = attachment.getOriginalName();
        this.name = attachment.getName();
        this.type = attachment.getType();
        this.size = attachment.getSize();
        this.path = attachment.getPath();
        this.status = attachment.getStatus();
    }
}
