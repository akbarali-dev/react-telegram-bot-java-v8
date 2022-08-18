package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.Product;
import uz.akbarali.foodappjavav8.projection.ProductProjection;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
//    @Query(nativeQuery = true,
//    value = "select name_uz as name,\n" +
//            "       price, ac.data\n" +
//            "from product p\n" +
//            "join attachments a on a.id = p.attachment_id\n" +
//            "join attachment_contents ac on a.id = ac.attachment_id")
//    List<ProductProjection> getAllProduct();


    @Override
    @Query(nativeQuery = true,
            value = "select cast(p.id as varchar) as id,\n" +
                    "       name_uz               as name,\n" +
                    "       price,\n" +
                    "       ac.data as image\n" +
                    "from product p\n" +
                    "         join attachments a on a.id = p.attachment_id\n" +
                    "         join attachment_contents ac on a.id = ac.attachment_id")
    List<ProductProjection> getAll();
}
