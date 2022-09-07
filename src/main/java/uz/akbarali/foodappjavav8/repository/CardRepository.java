package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.Card;
import uz.akbarali.foodappjavav8.projection.CategoryProductBotProjection;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends BaseRepository<Card> {

    @Query(nativeQuery = true, value = "select increment_order_count(:chatId, :productId);")
    Integer getUserOrderCountIncrement(Long chatId, UUID productId);

    @Query(nativeQuery = true, value = "select decrement_order_count(:chatId, :productId);")
    Integer getUserOrderCountDecrement(Long chatId, UUID productId);

    @Query(nativeQuery = true, value = "select add_or_remove_card(:added, :productId, :chatId);")
    Integer addOrRemoveProductFromCard(Long chatId, UUID productId, boolean added);

    @Query(nativeQuery = true, value = "select c.name_uz as name,\n" +
            "       cast(json_agg(json_build_object('id', p.id, 'name', p.name_uz, 'price', p.price, 'quantity', c2.quantity, 'cardId', c2.id)) as text) as foods\n" +
            "from category c\n" +
            "         join product p on c.id = p.category_id\n" +
            "join card c2 on p.id = c2.product_id\n" +
            "join users u on u.id = c2.user_id\n" +
            "join user_activity ua on u.id = ua.user_id\n" +
            "where added_card = true and success = false and ua.chat_id = :chatId\n" +
            "group by c.id")
    List<CategoryProductBotProjection> getAllCard(Long chatId);




}
