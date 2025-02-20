package com.bia.todolist.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bia.todolist.enums.ProfileEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bia.todolist.Exceptions.DataBindingViolationException;
import com.bia.todolist.Exceptions.ObjectNotFoundException;
import com.bia.todolist.controller.DTOs.UserDto;
import com.bia.todolist.model.User;
import com.bia.todolist.repositories.TaskRepository;
import com.bia.todolist.repositories.UserRepository;


@Service
public class UserService {
    private  UserRepository userRepository;
    private  TaskRepository taskRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    
    }

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuario não encontrado! id:" + id + ",tipo:" + User.class.getName()
        ));
    }

    public Long create(UserDto userDto) {
         User user = new User();
        user.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        user.setPassword(this.bCryptPasswordEncoder.encode(userDto.password()));
        
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
        newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas ");
        }
    }
}
