package uz.akbarali.foodappjavav8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.dto.CardDto;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.repository.CardRepository;
import uz.akbarali.foodappjavav8.repository.UserRepository;
import uz.akbarali.foodappjavav8.service.AnswerService;
import uz.akbarali.foodappjavav8.service.CardService;

import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    final
    CardRepository
            cardRepository;

    final
    UserRepository userRepository;

    final
    AnswerService answerService;



    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository, AnswerService answerService) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.answerService = answerService;
    }


    @Override
    public HttpEntity<ApiResponse> getAllCard() {
        return null;
    }

    @Override
    public HttpEntity<?> getCardById(UUID id) {
        return null;
    }

    @Override
    public HttpEntity<ApiResponse> saveCard(CardDto productDto, Errors error) {
        final boolean existsById = userRepository.existsById(productDto.getOrderId());
        if (!existsById) {
            return answerService.answer("order not found", false, null, HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public HttpEntity<?> deleteCardById(UUID id) {
        return null;
    }
}
