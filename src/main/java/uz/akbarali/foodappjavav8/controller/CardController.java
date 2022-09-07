package uz.akbarali.foodappjavav8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.akbarali.foodappjavav8.dto.CardDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.service.CardService;

import javax.validation.Valid;

@RequestMapping
@RestController
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<ApiResponse> saveOrder(@Valid @RequestBody CardDto cardDto, Errors errors) {

        return cardService.saveCard(cardDto, errors);
    }

}
