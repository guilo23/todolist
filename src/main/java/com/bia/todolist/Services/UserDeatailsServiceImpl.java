package com.bia.todolist.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bia.todolist.model.User;
import com.bia.todolist.repositories.UserRepository;
import com.security.UserSpringSecurity;

@Service
public class UserDeatailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = this.userRepository.findByUsername(username);
      if(Objects.isNull(user)){
        throw new UsernameNotFoundException("User not found");
      }
      return new UserSpringSecurity(user.getIds(), user.getPassword(), user.getUsername(), user.getProfiles());
    }
    
}
