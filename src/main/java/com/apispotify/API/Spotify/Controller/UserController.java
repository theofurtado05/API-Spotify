package com.apispotify.API.Spotify.Controller;


import com.apispotify.API.Spotify.Model.Users;
import com.apispotify.API.Spotify.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private String nonNullOrDefault(String value, String defaultValue) {
        return value != null && !value.isEmpty() ? value : defaultValue;
    }
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/getAll")
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getById(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id).map(user -> {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user) {
        Users savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) {
        return userRepository.findById(id).map(users -> {
            users.setName(nonNullOrDefault(userDetails.getName(), users.getName()));
            users.setEmail(nonNullOrDefault(userDetails.getEmail(), users.getEmail()));
            users.setPassword(nonNullOrDefault(userDetails.getPassword(), users.getPassword()));
            users.setLikedSongs(nonNullOrDefault(userDetails.getLikedSongs(), users.getLikedSongs()));
            users.setCurrentPlan(nonNullOrDefault(userDetails.getCurrentPlan(), users.getCurrentPlan()));

            Users updatedUser = userRepository.save(users);
            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND));
    }

}