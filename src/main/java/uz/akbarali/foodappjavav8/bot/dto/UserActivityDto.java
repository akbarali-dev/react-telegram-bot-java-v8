package uz.akbarali.foodappjavav8.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserActivityDto {
    private Long chatId;
    private int round;
    private UUID userId;
}
