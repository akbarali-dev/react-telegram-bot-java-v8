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
}
