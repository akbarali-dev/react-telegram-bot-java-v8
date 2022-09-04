package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.Card;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

public interface CardRepository extends BaseRepository<Card> {

@Query(nativeQuery = true, value = "select c.quantity\n" +
        "from card c\n" +
        "join product p on p.id = c.product_id\n" +
        "join users u on u.id = c.user_id\n" +
        "join user_activity ua on u.id = ua.user_id\n" +
        "where c.success = false and ua.chat_id = :chatId")
    Integer getUserOrderCount(Long chatId);
}
