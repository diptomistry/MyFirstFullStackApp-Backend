package com.dipto.demo.controller;

import com.dipto.demo.exception.UserNotFoundException;
import com.dipto.demo.model.User;
import com.dipto.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//to handle the requests made by the client;The RestController allows to handle all REST APIs such as GET, POST, Delete, PUT requests.
@CrossOrigin("http://localhost:3000/")//to connect with frontend
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);

    }
    //to get the data:
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();//findall is a method in jpa

    }
    //to get the user data with specific id from website
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                //if anyone types the invalid id
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);//save to database
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
