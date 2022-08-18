package uz.akbarali.foodappjavav8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity<ApiResponse> getAllProduct(){
        return categoryService.getAllCategory();
    }

}
