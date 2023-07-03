package rs.ac.bg.etf.funeral.company.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.etf.funeral.company.backend.entity.User;
import rs.ac.bg.etf.funeral.company.backend.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/user")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
