package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.repository.FoodRepository;
import by.it.academy.foodorder.parent.services.interfaces.CategoryService;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public List<Food> getAllDishes() {
        return (List<Food>) foodRepository.findAll();
    }


    @Override
    @Transactional
    public Food addNewDishes(Food food) {
        log.info("Add new food: {}", food);
        food.setCategory(categoryService.getCategoryById(food.getCategory().getCategoryId()));
        saveImage(food);
        return foodRepository.save(food);
    }

    @Override
    @Transactional
    public void removeDish(Long id) {
        log.info("Delete food with id: {}", id);
        foodRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Food getByFoodId(Long id) {
        return foodRepository.findByFoodId(id);
    }

    @Override
    @Transactional
    public Optional<Food> getAllByCategory(Category category) {
        return foodRepository.findAllByCategory(category);
    }

    @Override
    @Transactional
    public List<Food> getAllByCategoryName(String name) {
        return foodRepository.getAllByCategoryCategoryName(name);
    }

    @Override
    public void save(Food food) {
        saveImage(food);
        foodRepository.save(food);
    }


    public void saveImage(Food food){
        if (food.getFileData() != null) {
            byte[] image = null;
            try {
                image = food.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                food.setImage(image);
            }
        }
    }
}
