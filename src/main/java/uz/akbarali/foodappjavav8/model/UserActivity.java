package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserActivity extends AbsEntity {
    private Long chatId;
    private int round = 0;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserActivity(Long chatId, User user) {
        this.chatId = chatId;
        this.user = user;
    }

    public UserActivity(UUID id, Long chatId, int round) {
        super(id);
        this.chatId = chatId;
        this.round = round;
    }
}
