package uz.akbarali.foodappjavav8.repository;

import org.springframework.data.jpa.repository.Query;
import uz.akbarali.foodappjavav8.model.Category;
import uz.akbarali.foodappjavav8.projection.CategoryProjection;
import uz.akbarali.foodappjavav8.repository.base.BaseRepository;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category> {
    @Override
    @Query(nativeQuery = true, value = "select cast(id as varchar),\n" +
            "    name_uz as name\n" +
            "from category")
    List<CategoryProjection> getAll();

//    @Override
//    @Query(nativeQuery = true, value = "select cast(c.id as varchar),\n" +
//            "       c.name_uz as name,\n" +
//            "       cast(json_agg(json_build_object('id', p.id, 'name', p.name_uz, 'price', p.price, 'image', ac.data )) as text) as foods\n" +
//            "from category c\n" +
//            "join product p on c.id = p.category_id\n" +
//            "join attachments a on a.id = p.attachment_id\n" +
//            "join attachment_contents ac on a.id = ac.attachment_id\n" +
//            "group by c.id")
//    List<CategoryProjection> getAll();
}
