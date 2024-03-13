package com.CK.repository;

import com.CK.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<Auth, String> {
    boolean existsByUsername(String username);

    Optional<Auth> findByUsernameAndPassword(String username, String password);
}
