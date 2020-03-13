package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.repository.BasketRepository;
import by.it.academy.foodorder.parent.services.interfaces.BasketService;
import by.it.academy.foodorder.parent.services.interfaces.FoodService;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BasketController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private BasketService basketService;

    @RequestMapping(value = "/buyFood/{id}")
    public String buyFood(@PathVariable String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName()!=null){
            String name = auth.getName();
            Basket basket = basketService.getBasketByUsername(name);
            Food food = foodService.getByFoodId(Long.valueOf(id));
            food.getBasket().add(basket);
            basket.getFood().add(food);
            basketService.saveBasket(basket);
        }
        return "redirect:/myBasket";
    }

    @RequestMapping(value = "/myBasket", method = RequestMethod.GET)
    public String getBasket(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName()!=null) {
            String name = auth.getName();
            Basket basket = basketService.getBasketByUsername(name);
            List<Food> foodList = basket.getFood();
            model.addAttribute("basket", foodList);
        }
        return "myBasket";
    }

    @RequestMapping(value = "/removeFood/{id}")
    public String removeFood(@PathVariable String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(auth.getName()!=null){
            Basket basket = basketService.getBasketByUsername(name);
            Food food = foodService.getByFoodId(Long.valueOf(id));
            food.getBasket().remove(basket);
            basket.getFood().remove(food);
            basketService.saveBasket(basket);
        }
        return "redirect:/myBasket";
    }

}
