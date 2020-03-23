package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import by.it.academy.foodorder.parent.validator.PasswordValidator;
import by.it.academy.foodorder.parent.validator.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PhoneValidator phoneValidator;

    @RequestMapping(value = "/myAccount")
    public String account(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("user", userService.getByUsername(name));
        return "myAccount";
    }


    @RequestMapping(value = "/changePhone/{id}", method = RequestMethod.GET)
    public String getPhone(@PathVariable String id, Model model){

        User user = userService.getUserById(Long.valueOf(id));

        if(user!=null){
            model.addAttribute("user", user);
            return "changePhone";
        }

        return "myAccount";
    }

    @RequestMapping(value = "/changePhone", method = RequestMethod.POST)
    public String changePhone(@ModelAttribute @Valid User user, BindingResult bindingResult){

        phoneValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return "changePhone";
        }

        userService.saveUser(user);
        return "redirect:/myAccount";
    }

    @RequestMapping(value = "/changePassword/{id}", method = RequestMethod.GET)
    public String getPassword(@PathVariable String id, Model model){

        User user = userService.getUserById(Long.valueOf(id));

        if(user!=null){
            model.addAttribute("user", user);
            return "changePassword";
        }

        return "myAccount";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute @Valid User user, BindingResult bindingResult){

        passwordValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return "changePassword";
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return "redirect:/myAccount";
    }

}
