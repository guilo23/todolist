package com.bia.todolist.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bia.todolist.controller.DTOs.UserDto;
import com.bia.todolist.model.User;
import com.bia.todolist.repositories.TaskRepository;
import com.bia.todolist.repositories.UserRepository;

@Service
public class UserService {
    private  UserRepository userRepository;
    private  TaskRepository taskRepository;

    
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    
    }

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id:" + id + ",tipo:" + User.class.getName()
        ));
    }

    public Long create(UserDto userDto) {
         User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        
        // Salve o usuário
        var userSaved = userRepository.save(user);
    
        // Verifique o que está sendo retornado
        if (userSaved == null) {
            throw new RuntimeException("Usuário não foi salvo. Retorno nulo.");
        }
    
        // Log o ID gerado
        System.out.println("Usuário salvo com ID: " + userSaved.getIds());
        
        return userSaved.getIds();  // Retorna o ID gerado
    }
    
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
