package uz.akbarali.foodappjavav8.service.impl;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import uz.akbarali.foodappjavav8.bot.Bot;
import uz.akbarali.foodappjavav8.dto.CardDto;
import uz.akbarali.foodappjavav8.dto.ProductDto;
import uz.akbarali.foodappjavav8.model.Card;
import uz.akbarali.foodappjavav8.model.Product;
import uz.akbarali.foodappjavav8.model.User;
import uz.akbarali.foodappjavav8.payload.ApiResponse;
import uz.akbarali.foodappjavav8.repository.CardRepository;
import uz.akbarali.foodappjavav8.repository.ProductRepository;
import uz.akbarali.foodappjavav8.repository.UserRepository;
import uz.akbarali.foodappjavav8.service.AnswerService;
import uz.akbarali.foodappjavav8.service.CardService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    final
    ProductServiceImpl productService;
    final
    ProductRepository productRepository;
    final
    Bot bot;



    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository, AnswerService answerService, ProductServiceImpl productService, Bot bot, ProductRepository productRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.answerService = answerService;
        this.productService = productService;
        this.bot = bot;
        this.productRepository = productRepository;
    }


    @Override
    public HttpEntity<ApiResponse> getAllCard() {
        return null;
    }

    @Override
    public HttpEntity<?> getCardById(UUID id) {
        return null;
    }

    @SneakyThrows
    @Transactional
    @Override
    public HttpEntity<ApiResponse> saveCard(CardDto cardDto, Errors error) {
        final Optional<User> user = userRepository.findById(cardDto.getOrderId());
        if (!user.isPresent()) {
            return answerService.answer("order not found", false, null, HttpStatus.NOT_FOUND);
        }
        List<UUID> productsId = new ArrayList<>();
        for (ProductDto product : cardDto.getProducts()) {
            productsId.add(product.getId());
        }
        if (!productService.existByAllId(productsId)) {
            return answerService.answer("Product not found", false, null, HttpStatus.NOT_FOUND);
        }


        List<Card> cards = new ArrayList<>();
        for (ProductDto product : cardDto.getProducts()) {
            Optional<Product> byId = productRepository.findById(product.getId());
            if (!byId.isPresent()) {
                throw new NotFoundException("Product not found");
            }
            cards.add(new Card(byId.get(), user.get(), product.getCount(), byId.get().getPrice(), true, false, null));
        }
        final Long chatId = cardRepository.clearCardAndReturnChatId(user.get().getId());
        if (chatId==null) {
            return answerService.answer("Telegram bot not started", false, null, HttpStatus.CONFLICT);
        }
        bot.confirmationOrder(chatId);
        cardRepository.saveAll(cards);
            return answerService.answer("success", true, null, HttpStatus.OK    );

    }

    @Override
    public HttpEntity<?> deleteCardById(UUID id) {
        return null;
    }
}
