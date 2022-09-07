package uz.akbarali.foodappjavav8.projection;

import java.util.UUID;

public interface ProductCardProjection extends IdProjection{
    String getName();
    double getPrice();
    int getQuantity();
    UUID getCardId();
}
