package com.bia.todolist.Services;

import com.bia.todolist.model.User;
import com.bia.todolist.repositories.TaskReporitory;
import com.bia.todolist.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private TaskReporitory taskReporitory;

    public UserService(TaskReporitory taskReporitory, UserRepository userRepository) {
        this.taskReporitory = taskReporitory;
        this.userRepository = userRepository;
    }
    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id:" + id + ",tipo:" + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj){
        obj.setIds(null);
        var object = this.userRepository.save(obj);
        return object;
    }
    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getIds());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }
    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas ");
        }
    }
}
