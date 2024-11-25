package uz.ppdiary.pp_diary.service;

import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.DiaryDto;
import uz.ppdiary.pp_diary.dto.request.DiaryUpdateDto;

import java.util.List;

@Service
public interface DiaryService {
    DiaryDto save(DiaryDto diaryDto);
    DiaryDto getById(Long id);
    DiaryDto update(DiaryUpdateDto diaryUpdateDto);
    List<DiaryDto> getAll();
    List<DiaryDto> getAllByAuthorUsername(String username);
    void deleteById(Long id);
}
