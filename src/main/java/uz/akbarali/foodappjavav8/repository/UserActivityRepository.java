package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.akbarali.foodappjavav8.model.UserActivity;

import java.util.Optional;
import java.util.UUID;

public interface UserActivityRepository extends JpaRepository<UserActivity, UUID> {

    boolean existsByChatId(Long chatId);


    Optional<UserActivity> findByChatId(Long chatId);
}
