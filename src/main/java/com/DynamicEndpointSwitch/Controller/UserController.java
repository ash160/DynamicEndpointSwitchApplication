package com.DynamicEndpointSwitch.Controller;

import com.DynamicEndpointSwitch.Entity.User;
import com.DynamicEndpointSwitch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")    public List<User> getUsers() {
        return userService.getAllUsers();
    }

}
