package com.bia.todolist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bia.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_ids(Long id);
    //@Query(value = "Select t FROM Task t WHERE t.USER.id = :id")
    //List<Task> findByUser_id(@Param("id") Long id);
}
