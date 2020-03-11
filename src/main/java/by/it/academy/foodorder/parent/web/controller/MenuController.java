package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.services.interfaces.CategoryService;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenu(Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("menu", categoryList);
        return "menu";
    }

    @RequestMapping(value = "/menu/{categoryName}")
    public String getMenuByName(Model model, @PathVariable String categoryName){
        List<Food> foodList = foodService.getAllByCategoryName(categoryName);
        model.addAttribute("menuByCategory", foodList);
        return "menuList";
    }

}
