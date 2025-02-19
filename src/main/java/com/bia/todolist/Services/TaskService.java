package com.bia.todolist.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bia.todolist.model.Task;
import com.bia.todolist.model.User;
import com.bia.todolist.repositories.TaskRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;    
    private UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }


    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }
    @Transactional
    public  Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getIds());
        obj.setUser(user);
        var object = this.taskRepository.save(obj);
        return object;
    }
    public List<Task> findAllByUserId(Long id){
        List<Task> tasks = this.taskRepository.findByUser_ids(id);
        return tasks;
    }
    public Task update(Task obj){
      Task newObj = findById(obj.getIds());
      newObj.setDescription(obj.getDescription());
      return this.taskRepository.save(newObj);
    }
    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Task not RM");
        }
    }

}
