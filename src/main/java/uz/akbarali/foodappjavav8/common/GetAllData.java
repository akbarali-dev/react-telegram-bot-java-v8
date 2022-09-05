package uz.akbarali.foodappjavav8.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.akbarali.foodappjavav8.projection.ProductCategoryBotProjection;
import uz.akbarali.foodappjavav8.repository.ProductRepository;

import static uz.akbarali.foodappjavav8.bot.util.Util.*;

import uz.akbarali.foodappjavav8.bot.util.Util.*;

import java.util.*;

@Service
public class GetAllData {

    public Set<String> getAllCategory() {
        Set<String> categories = new HashSet<>();
        for (ProductCategoryBotProjection getAllDatum : getAllData) {
            categories.add(getAllDatum.getCategoryName());
        }
        return categories;
    }

    public ProductCategoryBotProjection getProductByName(String productName) {
        for (ProductCategoryBotProjection getAllDatum : getAllData) {
            if (productName.equals(getAllDatum.getProductName())) {
                return getAllDatum;
            }
        }
        return null;
    }
    public ProductCategoryBotProjection getProductById(UUID productId) {
        for (ProductCategoryBotProjection getAllDatum : getAllData) {
            if (productId.equals(getAllDatum.getId())) {
                return getAllDatum;
            }
        }
        return null;
    }

    public List<String> gelAllProductByCategoryName(String categoryName) {
        List<String> getAllProduct = new ArrayList<>();
        for (ProductCategoryBotProjection getAllDatum : getAllData) {
            if (getAllDatum.getCategoryName().equals(categoryName))
                getAllProduct.add(getAllDatum.getProductName());
        }
        return getAllProduct;
    }
}

