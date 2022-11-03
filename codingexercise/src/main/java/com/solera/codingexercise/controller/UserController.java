package com.solera.codingexercise.controller;

import com.solera.codingexercise.exception.DuplicateUserException;
import com.solera.codingexercise.model.User;
import com.solera.codingexercise.repository.UserRepository;
import com.solera.codingexercise.service.UserService;
import java.rmi.ServerException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserRepository repositoryUser;
    @Autowired
    private UserService service;
    @GetMapping
    public List<User> users () {
        return (List<User>) repositoryUser.findAll();
    }

    @PostMapping
    public ResponseEntity<User> register (@RequestBody User user) {
        User uSaved;

        try {
            uSaved = service.register(user);
        } catch(DuplicateUserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(uSaved);
    }

}
