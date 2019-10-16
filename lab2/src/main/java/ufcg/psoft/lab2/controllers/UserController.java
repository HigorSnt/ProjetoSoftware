package ufcg.psoft.lab2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab2.entities.users.User;
import ufcg.psoft.lab2.services.UserService;

@RestController
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        this.service.addUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
