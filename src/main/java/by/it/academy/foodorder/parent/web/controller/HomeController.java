package by.it.academy.foodorder.parent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/welcome"})
    public String home(){
        return "welcome";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

}
