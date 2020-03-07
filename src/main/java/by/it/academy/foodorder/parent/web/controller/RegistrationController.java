package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class RegistrationController {

    private String errorMessage;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getRegistrationUser(Model model){
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String saveRegistrationUser(Model model,
                                       @ModelAttribute("user") User user) {
        if (user.getUsername() == null || user.getUsername().length() == 0 ||
                user.getPassword() == null || user.getPassword().length() == 0) {
            errorMessage = "Incorrect data, please fill in all forms";
            model.addAttribute("errorMessage", errorMessage);
            return "signUp";
        } else if (!userService.existsByUsername(user.getUsername())) {
            errorMessage = "Name is already exists";
            model.addAttribute("errorMessage", errorMessage);
            return "signUp";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

}

