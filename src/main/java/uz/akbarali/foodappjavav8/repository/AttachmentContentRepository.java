package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.akbarali.foodappjavav8.model.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
}
