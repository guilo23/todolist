package com.bia.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bia.todolist.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    
    @Transactional(readOnly = true)
    User findByUsername(String username);
}
