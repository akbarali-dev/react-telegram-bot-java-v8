package uz.akbarali.foodappjavav8.service;


import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.dto.CardDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;

import java.util.UUID;

public interface CardService {
    HttpEntity<ApiResponse> getAllCard();

    HttpEntity<?> getCardById(UUID id);

    HttpEntity<ApiResponse> saveCard(CardDto productDto, Errors error);

    HttpEntity<?> deleteCardById(UUID id);
}
