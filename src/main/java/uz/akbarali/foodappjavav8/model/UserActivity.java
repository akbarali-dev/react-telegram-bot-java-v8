package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserActivity extends AbsEntity {
    private Long chatId;
    private int round;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
