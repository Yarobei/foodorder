package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.services.interfaces.SecurityService;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import by.it.academy.foodorder.parent.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
            userValidator.validate(userForm, bindingResult);

            if(bindingResult.hasErrors()){
                return "registration";
            }
            userService.saveUser(userForm);
            securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
            return "redirect:/welcome";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout){
        if(error != null){
            model.addAttribute("error", "Username and password is invalid");
        }
        if(logout != null){
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";
    }


}
