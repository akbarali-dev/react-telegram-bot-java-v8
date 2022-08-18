package uz.akbarali.foodappjavav8.bot.util;

import org.springframework.stereotype.Service;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserUtil {
    public Map<Long, UserActivityDto> activityMap = new HashMap<>();
}
