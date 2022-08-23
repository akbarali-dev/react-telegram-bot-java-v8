package uz.akbarali.foodappjavav8.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;
import uz.akbarali.foodappjavav8.projection.IdProjection;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<T extends AbsEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {


    @Query(nativeQuery = true, value = "select cast(p.id as varchar) as id,\n" +
            "       name_uz               as name,\n" +
            "       price,\n" +
            "       ac.data\n" +
            "from product p\n" +
            "         join attachments a on a.id = p.attachment_id\n" +
            "         join attachment_contents ac on a.id = ac.attachment_id")
    <K extends IdProjection> List<K> getAll();
}
