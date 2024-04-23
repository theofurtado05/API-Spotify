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

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user) {
        Users savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}