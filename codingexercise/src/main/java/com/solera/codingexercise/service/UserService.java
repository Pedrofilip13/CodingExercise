package com.solera.codingexercise.service;

import com.solera.codingexercise.exception.DuplicateUserException;
import com.solera.codingexercise.model.User;
import com.solera.codingexercise.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User register(User user) throws DuplicateUserException {
        Optional<User> bdUser = repository.findByEmail(user.getEmail());

        if(bdUser.isPresent()) {
            throw new DuplicateUserException("User Already Exists!");
        }

        bdUser = repository.findByPhoneNumber(user.getPhoneNumber());
        if(bdUser.isPresent()) {
            throw new DuplicateUserException("User Already Exists!");
        }

        repository.save(user);

        return user;
    }

}
