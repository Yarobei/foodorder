package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.repository.BasketRepository;
import by.it.academy.foodorder.parent.services.impl.SecurityServiceImpl;
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
        String name = auth.getName();
        basketService.getBasketByUsername(name).getFood().add(foodService.getByFoodId(Long.valueOf(id)));
        return "redirect:/menu";
    }

    @RequestMapping(value = "/myBasket", method = RequestMethod.GET)
    public String getBasket(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Food> basket = basketService.getBasketByUsername(name).getFood();
        model.addAttribute("basket", basket);
        return "myBasket";
    }

    @RequestMapping(value = "/removeFood/{id}")
    public String removeFood(@PathVariable String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        basketService.getBasketByUsername(name).getFood().remove(foodService.getByFoodId(Long.valueOf(id)));
        return "redirect:/myBasket";
    }

}
