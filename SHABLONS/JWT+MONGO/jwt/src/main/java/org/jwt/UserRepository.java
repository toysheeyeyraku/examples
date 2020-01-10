package org.jwt;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;





public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByUsername(String name);
    
    
}
