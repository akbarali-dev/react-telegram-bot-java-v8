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
import uz.akbarali.foodappjavav8.projection.ProductProjection;
import uz.akbarali.foodappjavav8.repository.CategoryRepository;
import uz.akbarali.foodappjavav8.repository.ProductRepository;
import uz.akbarali.foodappjavav8.service.AnswerService;
import uz.akbarali.foodappjavav8.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AnswerService answerService;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public HttpEntity<ApiResponse> getAllProduct() {
//        return answerService.answer("SUCCESS", true, productRepository.getAll(), HttpStatus.OK);
        return answerService.getAllObject(categoryRepository);
//        return ResponseEntity.status(200).body(productRepository.getAllFoodV2()));


    }

    public HttpEntity<?> getAllProductV2() {
//        return answerService.answer("SUCCESS", true, productRepository.getAll(), HttpStatus.OK);
//        return answerService.getAllObject(categoryRepository);
        return ResponseEntity.status(200).body(productRepository.getAllFoodV2());


    }

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

    @Autowired
    Gson gson;

    public List<ProductProjection> allFoods(String foods) {
        List<ProductProjection> list = gson.fromJson(foods, List.class);
        return list;
    }


}


