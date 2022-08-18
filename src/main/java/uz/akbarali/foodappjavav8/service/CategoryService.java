package uz.akbarali.foodappjavav8.service;

import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.dto.CategoryDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;

import java.util.UUID;

public interface CategoryService {
    HttpEntity<ApiResponse> getAllCategory();

    HttpEntity<?> getCategoryById(UUID id);

    HttpEntity<?> saveCategory(CategoryDto productDto, Errors error);

    HttpEntity<?> deleteCategoryById(UUID id);
}
