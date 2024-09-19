package uz.ppdiary.pp_diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Diary;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.UserStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private UserStatus userStatus;
    private List<Long> diaryIds;
    private List<Long> followerIds;
    private List<Long> followingIds;
    private List<Long> friendIds;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.userStatus = user.getUserStatus();
        this.diaryIds = user.getDiaries().stream().map(Diary::getId).toList();
        this.followerIds = user.getFollowers().stream().map(User::getId).toList();
        this.followingIds = user.getFollowing().stream().map(User::getId).toList();
        this.friendIds = user.getFriends().stream().map(User::getId).toList();
    }
}
