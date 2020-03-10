package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

    List<Food> findByFoodId(Long id);

    Optional<Food> findAllByCategory(Category category);

    List<Food> getAllByCategoryCategoryName(String name);

}
