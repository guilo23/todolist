package com.bia.todolist.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bia.todolist.enums.ProfileEnum;
import com.bia.todolist.enums.ProfileEnum.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long ids;

    @Column(name = "username",length = 100,nullable = false,unique = true)
    @Size(min=2,max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password",length = 60,nullable = false)
    @Size(min=8,max=240)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Task> task = new ArrayList<Task>();

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = Access.WRITE_ONLY)
    @CollectionTable(name = "tb_profiles")
    @Column(name = "profiles",nullable = false)
    private Set<Integer> profiles = new HashSet<>();

    public Set<ProfileEnum> getProfiles(){
        return this.profiles.stream().map(x -> ProfileEnum.fromEnum(x)).collect(Collectors.toSet());
    }
}
