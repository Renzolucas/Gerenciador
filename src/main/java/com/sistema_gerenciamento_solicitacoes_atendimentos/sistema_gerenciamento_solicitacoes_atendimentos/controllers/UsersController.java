package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.controllers;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUserBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services.UsersService;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseListUsersDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseSearchEmailID;

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
    @GetMapping("/listagem")
    public ResponseEntity<List<ResponseListUsersDTO>> listarTodos() {
        //CONTROLER CHAMA O DTO LIMPO
        List<ResponseListUsersDTO> listaLimpa = userService.listarTodosUsuarios();
        return ResponseEntity.ok(listaLimpa);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSearchEmailID> buscarPorId(@PathVariable UUID id){
        ResponseSearchEmailID usuarioEncontradoPorId = userService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioEncontradoPorId);
    }
    @GetMapping("/buscar")
    public ResponseEntity<ResponseSearchEmailID> buscarPorEmail(@RequestParam String email){
        ResponseSearchEmailID usuarioEncontradoPorEmail = userService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(usuarioEncontradoPorEmail);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
