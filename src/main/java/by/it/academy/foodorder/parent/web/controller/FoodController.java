package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.services.interfaces.CategoryService;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

    private String errorMessage;

    @RequestMapping(value = "/foodList", method = RequestMethod.GET)
    public String foodList(Model model){
        List<Food> foodList = foodService.getAllDishes();
        model.addAttribute("foodList", foodList);
        return "foodList";
    }

    @RequestMapping(value = "/addFood", method = RequestMethod.GET)
    public String createFood(Model model){
        model.addAttribute("food", new Food());
        List<Category> list = categoryService.getAllCategories();
        model.addAttribute("category", list);
        return "addFood";
    }

    @RequestMapping(value = "/addFood", method = RequestMethod.POST)
    public String addFood(Model model, @ModelAttribute Food food){
        if(food.getName() == null || food.getName().length() == 0 ||
                food.getPrice() == null || food.getPrice() == 0 || food.getCookingTime() == null ||
                food.getIngredients() == null || food.getIngredients().length() == 0 ||
                food.getWeight()==null || food.getDiscount()==null  ){
            errorMessage = "Incorrect input fields";
            List<Category> list = categoryService.getAllCategories();
            model.addAttribute("category", list);
            model.addAttribute("errorMessage", errorMessage);
            return "addFood";
        }else if(food.getCategory()==null){
            errorMessage = "Please choose category";
            List<Category> list = categoryService.getAllCategories();
            model.addAttribute("category", list);
            model.addAttribute("errorMessage", errorMessage);
            return "addFood";
        }else{
            foodService.addNewDishes(food);
            return "redirect:/welcome";
        }
    }

    @RequestMapping(value = "/deleteFood/{id}")
    public String deleteFood(@PathVariable String id){
        foodService.removeDish(Long.valueOf(id));
        return "redirect:/foodList";
    }
}
