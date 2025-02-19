package com.bia.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bia.todolist.Services.UserService;
import com.bia.todolist.controller.DTOs.UserDto;
import com.bia.todolist.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private  UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
        System.out.println("UserService:" + userService);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> findById(@PathVariable("id") Long id){
        var obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    private ResponseEntity<Object> create( @RequestBody UserDto userDto){
        
       this.userService.create(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User obj){
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user_id}")
    private ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
