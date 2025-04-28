package com.bia.todolist.controller;

import com.bia.todolist.model.DTOs.UserUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bia.todolist.model.User;
import com.bia.todolist.services.UserService;
import com.bia.todolist.model.DTOs.UserDto;

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
    private ResponseEntity<Object> create( @RequestBody User userDto){
        
       this.userService.create(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateUser(@PathVariable("id") Long id,  @RequestBody UserUpdateDTO obj){
        obj.setId(id);
        User user = this.userService.fromDTO(obj);
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userid}")
    private ResponseEntity<Void> deleteUser(@PathVariable("userid") Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
