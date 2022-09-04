package uz.akbarali.foodappjavav8.projection;

import java.util.UUID;

public interface ProductCategoryBotProjection extends IdProjection {


    String getProductName();

    UUID getCategoryId();

    String getCategoryName();

    float getPrice();

    byte[] getImage();
}
