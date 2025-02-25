package com.bia.todolist.repositories;

import java.util.List;

import com.bia.todolist.model.projeto.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bia.todolist.model.Task;


public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUser_ids(Long id);
}
