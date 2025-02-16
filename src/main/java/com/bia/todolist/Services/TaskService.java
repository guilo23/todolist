package com.bia.todolist.Services;

import com.bia.todolist.model.Task;
import com.bia.todolist.model.User;
import com.bia.todolist.repositories.TaskReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskReporitory taskReporitory;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskReporitory.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }
    @Transactional
    public  Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getIds());
        obj.setId(null);
        obj.setUser(user);
        var object = this.taskReporitory.save(obj);
        return object;
    }
    public Task update(Task obj){
      Task newObj = findById(obj.getId());
      newObj.setDescription(obj.getDescription());
      return this.taskReporitory.save(newObj);
    }
    public void delete(Long id){
        findById(id);
        try {
            this.taskReporitory.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Task not RM");
        }
    }

}
