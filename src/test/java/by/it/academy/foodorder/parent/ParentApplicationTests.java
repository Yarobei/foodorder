package by.it.academy.foodorder.parent;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.repository.CategoryRepository;
import by.it.academy.foodorder.parent.repository.FoodRepository;
import by.it.academy.foodorder.parent.services.interfaces.CategoryService;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParentApplicationTests {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FoodService foodService;


    @Test
    void contextLoads() {

        Category category = new Category(3L);

        Food food = new Food(null, "Pizza", 13.0, 13,
                true, "qweryty", 13.0,
                13.0, category, null);

        food.setCategory(categoryService.getCategoryById(food.getCategory().getCategoryId()));

        foodService.addNewDishes(food);
        System.err.println(foodRepository.findAll());
    }

}
