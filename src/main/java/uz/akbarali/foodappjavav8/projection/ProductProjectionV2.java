package uz.akbarali.foodappjavav8.projection;

public interface ProductProjectionV2 extends IdProjection{
    String getName();
    double getPrice();
    byte[] getImage();
    String getCategory();
}
