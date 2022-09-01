package uz.akbarali.foodappjavav8.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryBotProjection extends IdProjection {
    UUID getProductId();

    String getProductName();

    UUID getCategoryId();

    String getCategoryName();

    float getPrice();

    byte[] getImage();
}
