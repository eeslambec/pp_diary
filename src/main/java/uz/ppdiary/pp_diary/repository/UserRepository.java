package uz.ppdiary.pp_diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.ppdiary.pp_diary.entity.User;
import uz.ppdiary.pp_diary.entity.enums.UserStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u WHERE u.username =?1 AND u.status <> 'DELETED'")
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM users u WHERE u.email =?1 AND u.status <> 'DELETED'")
    Optional<User> findByEmail(String email);
    List<User> findAllByStatus(UserStatus status);
}
