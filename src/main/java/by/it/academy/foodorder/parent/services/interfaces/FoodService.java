package by.it.academy.foodorder.parent.services.interfaces;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FoodService {

    List<Food> getAllDishes();

    Food addNewDishes(Food food);

    void removeDish(Long id);

    Food updateDish(Food food);

    Food getByFoodId(Long id);

    Optional<Food> getAllByCategory(Category category);

    List<Food> getAllByCategoryName(String name);

    void save(Food food);
}
