package uz.akbarali.foodappjavav8.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.dto.ProductDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.projection.ProductCardProjection;
import uz.akbarali.foodappjavav8.projection.ProductProjection;
import uz.akbarali.foodappjavav8.repository.CategoryRepository;
import uz.akbarali.foodappjavav8.repository.ProductRepository;
import uz.akbarali.foodappjavav8.service.AnswerService;
import uz.akbarali.foodappjavav8.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    final
    ProductRepository productRepository;

    final
    AnswerService answerService;

    final
    CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, AnswerService answerService, CategoryRepository categoryRepository, Gson gson) {
        this.productRepository = productRepository;
        this.answerService = answerService;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
    }

    @Override
    public HttpEntity<ApiResponse> getAllProduct() {
//        return answerService.answer("SUCCESS", true, productRepository.getAll(), HttpStatus.OK);
        return answerService.getAllObject(productRepository);

//        return ResponseEntity.status(200).body(productRepository.getAllFoodV2()));


    }

//    public HttpEntity<?> getAllProductV2() {
////        return answerService.answer("SUCCESS", true, productRepository.getAll(), HttpStatus.OK);
////        return answerService.getAllObject(categoryRepository);
//        return ResponseEntity.status(200).body(productRepository.getAllFoodV2());
//
//
//    }

    @Override
    public HttpEntity<?> getProductById(UUID id) {
        return null;
    }

    @Override
    public HttpEntity<?> saveProduct(ProductDto productDto, Errors error) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteProductById(UUID id) {
        return null;
    }

    final
    Gson gson;

    public List<ProductCardProjection> allFoods(String foods) {
        List<ProductCardProjection> list = gson.fromJson(foods, List.class);
        return list;
    }

    public boolean existByAllId(List<UUID> ids) {
        List<UUID> allId = productRepository.getAllId();
        int count = 0;
        for (UUID id : ids) {
            for (UUID uuid : allId) {
                if (id.equals(uuid)) {
                    allId.remove(uuid);
                    count++;
                    if (ids.size() == count)
                        return true;
                    break;
                }
            }
        }
        return count == ids.size();
    }


}


