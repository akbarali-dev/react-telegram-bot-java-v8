package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card extends AbsEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double quantity;

    private double price;

    private boolean addedCard = false;
    private boolean success = false;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
