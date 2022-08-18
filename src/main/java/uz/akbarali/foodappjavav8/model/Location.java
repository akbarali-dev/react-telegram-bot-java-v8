package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Location extends AbsEntity {
    private double latitude;
    private double longitude;
    private String description;
}
