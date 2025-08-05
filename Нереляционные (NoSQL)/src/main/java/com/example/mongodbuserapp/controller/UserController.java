package com.example.mongodbuserapp.controller;

import com.example.mongodbuserapp.model.User;
import com.example.mongodbuserapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE - POST /api/users
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // READ ALL - GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // READ BY ID - GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // READ BY EMAIL - GET /api/users/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE - PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // SEARCH BY NAME - GET /api/users/search/name/{name}
    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<User>> findUsersByName(@PathVariable String name) {
        List<User> users = userService.findUsersByName(name);
        return ResponseEntity.ok(users);
    }

    // SEARCH BY AGE - GET /api/users/search/age/{age}
    @GetMapping("/search/age/{age}")
    public ResponseEntity<List<User>> findUsersByAge(@PathVariable Integer age) {
        List<User> users = userService.findUsersByAge(age);
        return ResponseEntity.ok(users);
    }

    // SEARCH BY AGE GREATER THAN - GET /api/users/search/age/gt/{age}
    @GetMapping("/search/age/gt/{age}")
    public ResponseEntity<List<User>> findUsersByAgeGreaterThan(@PathVariable Integer age) {
        List<User> users = userService.findUsersByAgeGreaterThan(age);
        return ResponseEntity.ok(users);
    }

    // SEARCH BY AGE LESS THAN - GET /api/users/search/age/lt/{age}
    @GetMapping("/search/age/lt/{age}")
    public ResponseEntity<List<User>> findUsersByAgeLessThan(@PathVariable Integer age) {
        List<User> users = userService.findUsersByAgeLessThan(age);
        return ResponseEntity.ok(users);
    }

    // SEARCH BY AGE RANGE - GET /api/users/search/age/range?min={minAge}&max={maxAge}
    @GetMapping("/search/age/range")
    public ResponseEntity<List<User>> findUsersByAgeRange(
            @RequestParam Integer min,
            @RequestParam Integer max) {
        List<User> users = userService.findUsersByAgeBetween(min, max);
        return ResponseEntity.ok(users);
    }

    // SEARCH BY NAME CONTAINING - GET /api/users/search/name/contains/{name}
    @GetMapping("/search/name/contains/{name}")
    public ResponseEntity<List<User>> findUsersByNameContaining(@PathVariable String name) {
        List<User> users = userService.findUsersByNameContaining(name);
        return ResponseEntity.ok(users);
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User API is running!");
    }
} 