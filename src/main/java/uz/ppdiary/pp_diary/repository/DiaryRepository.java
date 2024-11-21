package uz.ppdiary.pp_diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.ppdiary.pp_diary.entity.Diary;
import uz.ppdiary.pp_diary.entity.User;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("SELECT d FROM Diary d WHERE d.author=?1 AND d.diaryStatus ='ACTIVE'")
    List<Diary> findAllByAuthor(User author);
}
