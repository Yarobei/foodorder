package by.it.academy.foodorder.parent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String home(){
        return "welcome";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

}
