package com.bia.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = false)
    private Long ids;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,updatable = false)
    private User user;

    @Column(name = "description", length = 255, nullable = true)
    @NotNull
    @NotEmpty
    @Size(min=1,max = 255)
    private String description;

    public Task() {
    }

    public Task(Long ids, String description, User user) {
        this.ids = ids;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return ids;
    }

    public void setId(Long id) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if(o == null)
            return false;
        if(!(o instanceof User))
            return false;
        User other = (User) o;
        if(this.ids == null)
            return false;
        else if (!this.ids.equals(other.getClass()))
            return false;
        return Objects.equals(this.ids,other.getClass()) && Objects.equals(this.user,other.getClass())
                && Objects.equals(this.description,other.getClass());


    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int results = 1;
        results = prime * results +((this.ids == null) ? 0 : this.ids.hashCode());
        return results;
    }
}
