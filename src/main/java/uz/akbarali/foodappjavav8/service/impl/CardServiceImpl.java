package uz.akbarali.foodappjavav8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.akbarali.foodappjavav8.repository.CardRepository;

@Service
public class CardServiceImpl {

    final
    CardRepository
            cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


}
