package com.postgresql.projdb.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.projdb.model.User;
import com.postgresql.projdb.repo.PersonRepo;
import com.postgresql.projdb.userHolder.UserBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}) // enable CORS for react vite
public class UserController {

    @Autowired
    private PersonRepo personRepo;


    @PostMapping("/addPerson")
    public ResponseEntity<User> createPerson(@RequestBody User user){
        User response  = personRepo.save(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchAll")
    public List<UserBody> getAllUser(){
        List<User> userList = personRepo.findAll();
        List<UserBody> userBodyList = new ArrayList<>();
        for(User user : userList){
            UserBody userBody = new UserBody(user.getName(), user.getEmail(), user.getMessage());
            userBodyList.add(userBody);
        }
        return userBodyList;
    }

    // @GetMapping("/fetchById/{id}")
    // public User getUser(@PathVariable Long id){
    //     User user = personRepo.findById(id).orElse(null);
    //     return user;
    // }
    
}


// 8080