package uz.ppdiary.pp_diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ppdiary.pp_diary.entity.enums.UserStatus;

import java.util.List;

@Builder
@Setter
@Getter
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private UserStatus userStatus;
    @OneToMany
    private List<Diary> diaries;
    @ManyToMany
    private List<User> followers;
    @ManyToMany
    private List<User> following;
    @ManyToMany
    private List<User> friends;
}