package uz.akbarali.foodappjavav8.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.repository.ProductRepository;
import uz.akbarali.foodappjavav8.service.ProductService;
import uz.akbarali.foodappjavav8.service.impl.ProductServiceImpl;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")

public class ProductController {
    final
    ProductRepository productRepository;

    final
    ProductService productService;

    final
    ProductServiceImpl productServiceImpl;

    public ProductController(ProductRepository productRepository, ProductService productService, ProductServiceImpl productServiceImpl) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productServiceImpl = productServiceImpl;
    }
    @ApiOperation("hamma category larni olish va category bilan birga productlarni olish")
    @GetMapping
    public HttpEntity<?> getAllProduct(){

//    @ApiOperation("hamma category larni olish va category bilan birga productlarni olish")
//    @GetMapping
//    public HttpEntity<ApiResponse> getAllProduct(){
//        return productService.getAllProduct();
//    }


        return productServiceImpl.getAllProductV2();
    }

//    @GetMapping
//    public HttpEntity<?> getAllProduct(){
//        return ResponseEntity.ok(productRepository.getAll());
//    }

}
