package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.services.interfaces.CategoryService;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

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
    public String addFood(Model model, @ModelAttribute @Valid Food food, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Category> list = categoryService.getAllCategories();
            model.addAttribute("category", list);
            return "addFood";
        } else{
            foodService.addNewDishes(food);
            return "redirect:/welcome";
        }
    }

    @RequestMapping(value = "/deleteFood/{id}")
    public String deleteFood(@PathVariable String id){
        foodService.removeDish(Long.valueOf(id));
        return "redirect:/foodList";
    }

    @RequestMapping(value = { "/productImage/{id}" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @PathVariable String id) throws IOException {
        Food food = null;
        if (id != null) {
            food = foodService.getByFoodId(Long.valueOf(id));
        }
        if (food != null && food.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(food.getImage());
        }
        response.getOutputStream().close();
    }
}