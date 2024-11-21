package uz.ppdiary.pp_diary.service;

import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.DiaryDto;
import uz.ppdiary.pp_diary.dto.request.DiaryUpdateDto;

import java.util.List;

@Service
public interface DiaryService {
    DiaryDto save(DiaryDto diaryDto);
    DiaryDto update(DiaryUpdateDto diaryUpdateDto);
    DiaryDto getById(Long id);
    List<DiaryDto> getAllByAuthorUsername(String username);
    List<DiaryDto> getAll();
    void deleteById(Long id);
}
