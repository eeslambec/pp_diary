package uz.ppdiary.pp_diary.service.impl;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.DiaryDto;
import uz.ppdiary.pp_diary.dto.request.DiaryUpdateDto;
import uz.ppdiary.pp_diary.entity.Attachment;
import uz.ppdiary.pp_diary.entity.Diary;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.DiaryStatus;
import uz.ppdiary.pp_diary.exceptions.NotFoundException;
import uz.ppdiary.pp_diary.repository.AttachmentRepository;
import uz.ppdiary.pp_diary.repository.DiaryRepository;
import uz.ppdiary.pp_diary.repository.UserRepository;
import uz.ppdiary.pp_diary.service.DiaryService;
import uz.ppdiary.pp_diary.util.Validations;
import uz.ppdiary.pp_diary.utils.Validations;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public DiaryDto save(DiaryDto diaryDto) {
        List<User> mentionedUsers = new ArrayList<>();
        List<Attachment> allMediaById = new ArrayList<>();
        if (diaryDto.getMentionedUsersUsernames() != null)
            mentionedUsers = userRepository.findAllByUsername(diaryDto.getMentionedUsersUsernames());

        if (diaryDto.getMediaIds() != null)
            allMediaById = attachmentRepository.findAllById(diaryDto.getMediaIds());

        return new DiaryDto(diaryRepository.save(Diary.builder()
                        .text(diaryDto.getText())
                        .title(diaryDto.getTitle())
                        .happenedDate(diaryDto.getHappenedDate())
                        .mentionedUsers(mentionedUsers)
                        .medias(allMediaById)
                .build()));
    }

    @Override
    public DiaryDto update(DiaryUpdateDto diaryUpdateDto) {
        Diary diary = diaryRepository.findById(diaryUpdateDto.getId()).get();
        diary.setTitle(Validations.requireNonNullElse(diaryUpdateDto.getTitle(), diary.getTitle()));
        diary.setText(Validations.requireNonNullElse(diaryUpdateDto.getText(), diary.getText()));
        diary.setHappenedDate(Validations.requireNonNullElse(diaryUpdateDto.getHappenedDate(), diary.getHappenedDate()));
        diary.setMedias(Validations.requireNonNullElse(diaryUpdateDto.getMedias(), diary.getMedias()));
        diary.setMentionedUsers(Validations.requireNonNullElse(diaryUpdateDto.getMentionedUsers(), diary.getMentionedUsers()));
        diary.setDiaryStatus(Validations.requireNonNullElse(diaryUpdateDto.getDiaryStatus(), diary.getDiaryStatus()));
        return new DiaryDto(diaryRepository.save(diary));
    }

    @Override
    public DiaryDto getById(@NotBlank Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new NotFoundException("Diary"));
        if (diary.getDiaryStatus() == DiaryStatus.DELETED)
            throw new NotFoundException("Diary");
        return new DiaryDto(diary);
    }

    @Override
    public List<DiaryDto> getAllByAuthorUsername(@NotBlank String username) {
        return diaryRepository.findAllByAuthor(userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User")))
                .stream().map(DiaryDto::new).toList();

    }

    @Override
    public List<DiaryDto> getAll() {
        return diaryRepository.findAll()
                .stream()
                .map(DiaryDto::new)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new NotFoundException("Diary"));
        if (!(diary.getDiaryStatus() == DiaryStatus.DELETED))
            diary.setDiaryStatus(DiaryStatus.DELETED);

    }
}
