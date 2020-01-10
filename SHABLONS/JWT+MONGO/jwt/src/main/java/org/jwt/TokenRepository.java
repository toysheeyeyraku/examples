package org.jwt;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;





public interface TokenRepository extends MongoRepository<Tokens, String> {

    public Optional<Tokens> findByUser(String name);
    
    
}
