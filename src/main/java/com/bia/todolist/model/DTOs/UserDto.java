package com.bia.todolist.model.DTOs;


public record UserDto(String username, String password) {
    public UserDto {
        if (username == null || username.isBlank()) {
            throw new RuntimeException("Nome de usuário não pode ser nulo ou vazio");
        }
        if (password == null || password.isBlank()) {
            throw new RuntimeException("Senha não pode ser nula ou vazia");
        }
    }
}
