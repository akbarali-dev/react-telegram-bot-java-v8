package uz.akbarali.foodappjavav8.model.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbsNameEntity extends AbsEntity {

    @Column(unique = true, columnDefinition="TEXT")
    @OrderBy
    private String nameUz;
    @Column(unique = true, columnDefinition="TEXT")
    private String nameRu;
    @Column(columnDefinition="TEXT")
    private String descriptionUz;
    @Column(columnDefinition="TEXT")
    private String descriptionRu;

    public AbsNameEntity(String nameUz, String nameRu) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
    }
}
