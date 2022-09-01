package uz.akbarali.foodappjavav8.bot.util;

import org.springframework.stereotype.Service;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;
import uz.akbarali.foodappjavav8.projection.ProductCategoryBotProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class Util {
    public Map<Long, UserActivityDto> activityMap = new HashMap<>();
    public static List<ProductCategoryBotProjection> getAllData = new ArrayList<>();

}
