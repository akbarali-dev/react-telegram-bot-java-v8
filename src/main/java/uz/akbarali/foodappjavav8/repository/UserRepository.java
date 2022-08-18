package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.akbarali.foodappjavav8.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
