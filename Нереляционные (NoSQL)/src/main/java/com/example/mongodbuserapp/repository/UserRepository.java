package com.example.mongodbuserapp.repository;

import com.example.mongodbuserapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find by name (case-insensitive)
    List<User> findByNameIgnoreCase(String name);

    // Find by age
    List<User> findByAge(Integer age);

    // Find by age greater than
    List<User> findByAgeGreaterThan(Integer age);

    // Find by age less than
    List<User> findByAgeLessThan(Integer age);

    // Find by email
    Optional<User> findByEmail(String email);

    // Custom query to find users by name containing
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<User> findByNameContainingIgnoreCase(String name);

    // Custom query to find users by age range
    @Query("{'age': {$gte: ?0, $lte: ?1}}")
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);

    // Check if user exists by email
    boolean existsByEmail(String email);
} 