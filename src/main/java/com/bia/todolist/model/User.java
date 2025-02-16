package com.bia.todolist.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User {
    public interface CreateUser{}
    public interface UpdateUser{}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private Long ids;

    @Column(name = "username",length = 100,nullable = false,unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(min=2,max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password",length = 60,nullable = false)
    @NotNull(groups = {CreateUser.class,UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class,UpdateUser.class})
    @Size(min=8,max=60)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> task = new ArrayList<Task>();


    public User() {
    }

    public User(Long ids, String password, String username) {
        this.ids = ids;
        this.password = password;
        this.username = username;
    }

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
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
        return Objects.equals(this.ids,other.getClass()) && Objects.equals(this.username,other.getUsername())
                && Objects.equals(this.password,other.getPassword());


    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int results = 1;
        results = prime * results +((this.ids == null) ? 0 : this.ids.hashCode());
        return results;
    }

}
