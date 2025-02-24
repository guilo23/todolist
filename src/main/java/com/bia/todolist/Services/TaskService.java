package com.bia.todolist.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.bia.todolist.Exceptions.AuthorizationException;
import com.bia.todolist.Exceptions.ObjectNotFoundException;
import com.bia.todolist.enums.ProfileEnum;
import com.bia.todolist.security.UserSpringSecurity;
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
        Task task = this.taskRepository.findById(id).orElseThrow(()->new ObjectNotFoundException(
                "Tarefa não encontrada"
        ));

        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if(Objects.isNull(userSpringSecurity) ||
                !userSpringSecurity.hasRole(ProfileEnum.ADMIN) && !(userHasTask(userSpringSecurity,task))) {

             throw new AuthorizationException("Task not found") ;
        }
        return task;

    }
    @Transactional
    public  Task create(Task obj){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if(Objects.isNull(userSpringSecurity)) {

            throw new AuthorizationException("Task not found") ;
        }
        User user = this.userService.findById(userSpringSecurity.getId());
        obj.setUser(user);
        var object = this.taskRepository.save(obj);
        return object;
    }
    public List<Task> findAllByUser(){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if(Objects.isNull(userSpringSecurity)) {

            throw new AuthorizationException("Task not found") ;
        }
        List<Task> tasks = this.taskRepository.findByUser_ids(userSpringSecurity.getId());
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
    private Boolean userHasTask (UserSpringSecurity userSpringSecurity,Task task){
        return task.getUser().getIds().equals(userSpringSecurity.getId());
    }

}
