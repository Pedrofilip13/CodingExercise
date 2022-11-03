package com.solera.codingexercise.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.solera.codingexercise.model.User;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
