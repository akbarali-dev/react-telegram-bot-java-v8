package uz.akbarali.foodappjavav8.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CategoryProjection extends IdProjection {
    String getName();

        @Value("#{@productRepository.getAllFood(target.id)}")
    List<ProductProjection> getFoods();
//    @Value("#{@productServiceImpl.allFoods(target.foods)}")
//    List<ProductProjection> getFoods();
}
