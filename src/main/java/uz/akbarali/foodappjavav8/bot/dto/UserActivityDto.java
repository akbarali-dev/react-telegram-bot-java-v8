package uz.akbarali.foodappjavav8.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.enums.Role;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserActivityDto {
    private UUID id;
    private Long chatId;
    private int round;
    private UUID userId;
    private Role role;
    private UUID selectFood;

    public UserActivityDto(UUID id, Long chatId, int round, UUID userId, Role role) {
        this.id = id;
        this.chatId = chatId;
        this.round = round;
        this.userId = userId;
        this.role = role;
    }
}
