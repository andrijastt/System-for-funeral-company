package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.etf.funeral.company.backend.entity.User;
import rs.ac.bg.etf.funeral.company.backend.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/user")
    public User saveUser(@Valid @RequestBody User user){
        System.out.println("user = " + user);
        return userService.saveUser(user);
    }
}
