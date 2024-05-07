package com.KeyCloakTest.KeyCloakTest.Controller;

import com.KeyCloakTest.KeyCloakTest.Models.Userdet;
import com.KeyCloakTest.KeyCloakTest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<Userdet> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Userdet getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public Userdet addUser(@RequestBody Userdet user) {
        return userService.saveUser(user);
    }
}
