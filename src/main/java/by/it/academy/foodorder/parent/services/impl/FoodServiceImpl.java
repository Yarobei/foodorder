package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.repository.FoodRepository;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAllDishes() {
        return (List<Food>) foodRepository.findAll();
    }


    @Override
    public Food addNewDishes(@NonNull Food food) {
        log.info("Add new food: {}", food);
        return foodRepository.save(food);
    }

    @Override
    public void removeDish(Long id) {
        log.info("Delete food with id: {}", id);
        foodRepository.deleteById(id);
    }

    @Override
    public Food updateDish(Food food) {
        if (foodRepository.existsById(food.getFoodId())){
            log.info("Update food: {}", food);
            foodRepository.delete(food);
            foodRepository.save(food);
            return food;
        }else{
            log.info("Update food: {}", food);
            return foodRepository.save(food);
        }
    }

    @Override
    public List<Food> getByFoodId(Long id) {
        return foodRepository.findByFoodId(id);
    }

    @Override
    public Optional<Food> getAllByCategory(Category category) {
        return foodRepository.findAllByCategory(category);
    }
}
