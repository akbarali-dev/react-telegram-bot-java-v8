package uz.akbarali.foodappjavav8.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test/hello")
@RestController
public class Controller {
    @GetMapping
    public HttpEntity<?> runner(){
        return ResponseEntity.ok("HELLO");
    }
}
