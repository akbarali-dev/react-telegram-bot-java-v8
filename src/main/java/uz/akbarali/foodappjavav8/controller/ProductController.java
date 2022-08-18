package uz.akbarali.foodappjavav8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.repository.ProductRepository;
import uz.akbarali.foodappjavav8.service.ProductService;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping
    public HttpEntity<ApiResponse> getAllProduct(){
        return productService.getAllProduct();
    }

//    @GetMapping
//    public HttpEntity<?> getAllProduct(){
//        return ResponseEntity.ok(productRepository.getAll());
//    }

}
