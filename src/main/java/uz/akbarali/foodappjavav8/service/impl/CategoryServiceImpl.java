package uz.akbarali.foodappjavav8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.dto.CategoryDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.repository.CategoryRepository;
import uz.akbarali.foodappjavav8.service.AnswerService;
import uz.akbarali.foodappjavav8.service.CategoryService;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    AnswerService answerService;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public HttpEntity<ApiResponse> getAllCategory() {
        return answerService.getAllObject(categoryRepository);
    }

    @Override
    public HttpEntity<?> getCategoryById(UUID id) {
        return null;
    }

    @Override
    public HttpEntity<?> saveCategory(CategoryDto productDto, Errors error) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteCategoryById(UUID id) {
        return null;
    }
}
