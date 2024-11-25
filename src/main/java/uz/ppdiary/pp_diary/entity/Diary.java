package uz.ppdiary.pp_diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ppdiary.pp_diary.entity.auditing.Auditing;
import uz.ppdiary.pp_diary.entity.enums.DiaryStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Diary extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    private User author;
    @OneToMany
    private List<Attachment> medias;
    @OneToMany
    private List<Comment> comments;
    @ManyToMany
    private List<User> mentionedUsers;
    @ManyToMany
    private List<Reaction> reactions;
    private LocalDate happenedDate;
    private DiaryStatus diaryStatus;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diary diary = (Diary) o;
        return Objects.equals(id, diary.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}