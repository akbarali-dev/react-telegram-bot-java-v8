package uz.akbarali.foodappjavav8.projection;

public interface ProductProjection extends IdProjection{
    String getName();
    double getPrice();
    byte getImage();
}
