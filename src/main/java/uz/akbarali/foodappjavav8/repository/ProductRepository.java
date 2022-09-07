package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.Product;
import uz.akbarali.foodappjavav8.projection.*;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends BaseRepository<Product> {
//    @Query(nativeQuery = true,
//    value = "select name_uz as name,\n" +
//            "       price, ac.data\n" +
//            "from product p\n" +
//            "join attachments a on a.id = p.attachment_id\n" +
//            "join attachment_contents ac on a.id = ac.attachment_id")
//    List<ProductProjection> getAllProduct();


    @Override
    @Query(nativeQuery = true, value = "select cast(p.id as varchar) as id,\n" +
            "       p.name_uz             as productName,\n" +
            "       cast(c.id as varchar) as categoryId,\n" +
            "       c.name_uz             as categoryName,\n" +
            "       ac.data               as image,\n" +
            "       p.price " +
            "from product p\n" +
            "         join category c on c.id = p.category_id\n" +
            "         join attachments a on a.id = p.attachment_id\n" +
            "         join attachment_contents ac on a.id = ac.attachment_id")
    List<ProductCategoryBotProjection> getAll();

    @Query(nativeQuery = true,
            value = "select cast(p.id as varchar) as id,\n" +
                    "       name_uz               as name,\n" +
                    "       price,\n" +
                    "       ac.data as image\n" +
                    "from product p\n" +
                    "         join attachments a on a.id = p.attachment_id\n" +
                    "         join attachment_contents ac on a.id = ac.attachment_id\n" +
                    "where p.category_id = :categoryId")
    List<ProductProjection> getAllFood(UUID categoryId);

;

    @Query(nativeQuery = true,
    value = "select p.name_uz as name\n" +
            "from product p\n" +
            "         join category c on c.id = p.category_id\n" +
            "where c.name_uz = :categoryName or c.name_ru = :categoryName")
    List<ProductBotProjection> getAllByCategoryProductBot(String categoryName);

//    @Query(
//            nativeQuery = true,
//            value = "select cast(p.id as varchar) as id,\n" +
//                    "       p.name_uz             as productName,\n" +
//                    "       cast(c.id as varchar) as categoryId,\n" +
//                    "       c.name_uz             as categoryName,\n" +
//                    "       ac.data               as image,\n" +
//                    "       p.price " +
//                    "from product p\n" +
//                    "         join category c on c.id = p.category_id\n" +
//                    "         join attachments a on a.id = p.attachment_id\n" +
//                    "         join attachment_contents ac on a.id = ac.attachment_id"
//    )
//    List<ProductCategoryBotProjection> getAllProductAndCategory();


}
