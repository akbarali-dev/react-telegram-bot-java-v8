package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.akbarali.foodappjavav8.model.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
