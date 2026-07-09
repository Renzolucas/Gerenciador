package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.controllers;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUserBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services.UsersService;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseListUsersDTO;

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
    //GET PARA LISTAR TODOS OS USUARIOS
    @GetMapping("/listagem_users")
    public ResponseEntity<List<ResponseListUsersDTO>> listarTodos() {
        //CONTROLER CHAMA O DTO LIMPO
        List<ResponseListUsersDTO> listaLimpa = userService.listarTodosUsuarios();
        return ResponseEntity.ok(listaLimpa);
    }
}
