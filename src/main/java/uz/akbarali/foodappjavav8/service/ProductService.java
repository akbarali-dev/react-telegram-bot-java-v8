package uz.akbarali.foodappjavav8.service;

import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.dto.ProductDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;

import java.util.UUID;

public interface ProductService {

    HttpEntity<ApiResponse> getAllProduct();

    HttpEntity<?> getProductById(UUID id);

    HttpEntity<?> saveProduct(ProductDto productDto, Errors error);

    HttpEntity<?> deleteProductById(UUID id);
}
