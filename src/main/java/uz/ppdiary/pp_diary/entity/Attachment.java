package uz.ppdiary.pp_diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ppdiary.pp_diary.entity.auditing.Auditing;
import uz.ppdiary.pp_diary.entity.enums.AttachmentStatus;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalName;
    private String name;
    private String type;
    private Long size;
    private String path;
    AttachmentStatus status;
}