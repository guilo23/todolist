package com.bia.todolist.controller;


import com.bia.todolist.Services.UserService;
import com.bia.todolist.model.User;
import com.bia.todolist.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    private ResponseEntity<User> findById(@PathVariable("user_id") Long id){
        var obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<User>>findAll(){
        return null;
    }

    @PostMapping
    @Validated(User.CreateUser.class)
    private ResponseEntity<Void> createUser(@Valid @RequestBody User obj){
       this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path( "/{user_id}").buildAndExpand(obj.getIds()).toUri();
       return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{user_id}")
    @Validated(User.UpdateUser.class)
    private ResponseEntity<Void> updateUser(@PathVariable("user_id") Long id,@Valid @RequestBody User obj){
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{user_id}")
    private ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
