package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.Food;
import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.services.interfaces.BasketService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUserById(Long.valueOf(id));
        return "redirect:/login";
    }

    @RequestMapping(value = "/getOrder")
    public String getOrder(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Food> foodList = userService.getByUsername(name).getBasket().getFood();
        Double sum = 0d;
        for (Food food: foodList) {
            sum+=food.getPrice()-((food.getDiscount()/100)*food.getPrice());
        }
        model.addAttribute("sum", sum);
        model.addAttribute("order", foodList);
        return "getOrder";
    }

    @RequestMapping(value = "/confirmOrder")
    public String confirmOrder(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Food> foodList = basketService.getBasketByUsername(name).getFood();
        Basket basket = basketService.getBasketByUsername(name);
        int counter = foodList.size()-1;
        while(!foodList.isEmpty()){
            Food food = foodList.get(counter);
            food.getBasket().remove(basket);
            basket.getFood().remove(food);
            counter--;
        }
        basketService.saveBasket(basket);
        return "finalizeOrder";
    }


}
