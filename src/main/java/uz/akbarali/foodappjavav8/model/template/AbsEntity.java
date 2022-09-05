package uz.akbarali.foodappjavav8.model.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbsEntity {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid default uuid_generate_v4()")
    private UUID id;


    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    public AbsEntity() {
    }

    public AbsEntity(UUID id) {
        this.id = id;
    }

    //
//    @CreatedBy
//    @Column(name = "created_by_id")
//    private UUID createdBy;
//
//    @LastModifiedBy
//    @Column(name = "updated_by_id")
//    private UUID updatedBy;
}
