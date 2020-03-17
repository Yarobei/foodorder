package by.it.academy.foodorder.parent.web.controller;

import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.repository.RoleRepository;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/changeRole/{id}")
    public String changeRole(@PathVariable String id){
        User newAdmin = userService.getUserById(Long.valueOf(id));
        newAdmin.setRoles(new HashSet<>(roleRepository.findByName("ROLE_ADMIN")));
        userService.saveUser(newAdmin);
        return "redirect:/userList";
    }

    @RequestMapping(value = "/adminDeleteUser/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUserById(Long.valueOf(id));
        return "redirect:/userList";
    }

}
