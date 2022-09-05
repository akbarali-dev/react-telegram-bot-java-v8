package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.Card;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

import java.util.UUID;

public interface CardRepository extends BaseRepository<Card> {

@Query(nativeQuery = true, value = "select increment_order_count(:chatId, :productId);")
    Integer getUserOrderCountIncrement(Long chatId, UUID productId);
@Query(nativeQuery = true, value = "select decrement_order_count(:chatId, :productId);")
    Integer getUserOrderCountDecrement(Long chatId, UUID productId);
}
