package uz.akbarali.foodappjavav8.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/v1/category-test")
public class CategoryControllerTest {


    @GetMapping
    public String getAllProduct(Model model) throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(savedImage("src/main/resources/images/burger.png"));
        String base64Encoded = new String(encodeBase64, "UTF-8");

        model.addAttribute("image", base64Encoded);
      return "index";
    }

    @GetMapping("/test")
    public String getAllProduct1(Model model) throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(savedImage("src/main/resources/images/coca.png"));
        String base64Encoded = new String(encodeBase64, "UTF-8");

        model.addAttribute("image", base64Encoded);
        return "index-test";
    }

    public byte[] savedImage(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
