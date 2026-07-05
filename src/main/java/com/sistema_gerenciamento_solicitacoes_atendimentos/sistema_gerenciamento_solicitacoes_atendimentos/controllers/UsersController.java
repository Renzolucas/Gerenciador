package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUserBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody CreateUserBodyDTO dto){
        Users createdUser = userService.create(dto);
        return ResponseEntity.ok(createdUser); // 200
    }
}
