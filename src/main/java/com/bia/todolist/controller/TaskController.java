package com.bia.todolist.controller;

import com.bia.todolist.Services.TaskService;
import com.bia.todolist.model.Task;
import com.bia.todolist.repositories.TaskReporitory;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    private TaskService taskService;
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task task = this.taskService.findById(id);
        return ResponseEntity.ok(task);
    }
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Task>> allTask(@PathVariable Long userId){
        List<Task> tasks = this.taskService.findAllByUserId(userId);
        return ResponseEntity.ok(tasks);
    }
    @PostMapping
    @Validated
    public ResponseEntity<Void> createTask(@Valid @RequestBody Task obj){
        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path( "/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @Valid @RequestBody Task obj){
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id,Task obj){
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }


}
