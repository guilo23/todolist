package com.bia.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bia.todolist.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    
    @Transactional(readOnly = true)
    User findByUsername(String username);
}
