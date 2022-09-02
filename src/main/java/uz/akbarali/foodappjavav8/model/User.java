package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.enums.Role;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbsEntity {
    private String fullName;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Role role) {
        this.role = role;
    }
}
