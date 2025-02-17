package com.bia.todolist.repositories;

import com.bia.todolist.model.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskReporitory extends JpaRepository<Task, Long> {
    List<Task> findByUser_ids(Long id);


    //@Query(value = "Select t FROM Task t WHERE t.USER.id = :id")
    //List<Task> findByUser_id(@Param("id") Long id);
}
