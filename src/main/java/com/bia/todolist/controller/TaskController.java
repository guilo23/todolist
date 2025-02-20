package com.bia.todolist.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bia.todolist.services.TaskService;
import com.bia.todolist.model.Task;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task task = this.taskService.findById(id);
        return ResponseEntity.ok(task);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> allTask(@PathVariable Long id){
        List<Task> tasks = this.taskService.findAllByUserId(id);
        return ResponseEntity.ok(tasks);
    }
    @PostMapping("/{id}")
    @Validated
    public ResponseEntity<Void> createTask(@PathVariable Long id, @RequestBody Task obj){
        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path( "/{userid}").buildAndExpand(obj.getIds()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Task obj){
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id,Task obj){
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }


}
