package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends AbsNameEntity {
    private double price;
    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String nameUz, String nameRu, String descriptionUz, String descriptionRu, double price, Attachment attachment, Category category) {
        super(nameUz, nameRu, descriptionUz, descriptionRu);
        this.price = price;
        this.attachment = attachment;
        this.category = category;
    }


}
