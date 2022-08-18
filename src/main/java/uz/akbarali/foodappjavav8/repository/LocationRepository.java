package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.akbarali.foodappjavav8.model.Location;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
