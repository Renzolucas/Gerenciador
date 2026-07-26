package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.controllers;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUsersBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseUsersDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.UpdateUsersDTO;
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
    public ResponseEntity<ResponseUsersDTO> createUser(@Valid @RequestBody CreateUsersBodyDTO dto){
        ResponseUsersDTO createdUser = userService.create(dto);
        return ResponseEntity.ok(createdUser); // 200
    }
    //GET PARA LISTAR TODOS OS USUARIOS
    @GetMapping("/listagem")
    public ResponseEntity<List<ResponseUsersDTO>> listarTodos() {
        //CONTROLER CHAMA O DTO LIMPO
        List<ResponseUsersDTO> listaLimpa = userService.listarTodosUsuarios();
        return ResponseEntity.ok(listaLimpa);
    }
    //GET PARA BUSCAR INFO DOS USUARIOS COM BASE EM SEU ID OU EMAIL
    @GetMapping("/buscar")
    public ResponseEntity<ResponseUsersDTO> buscarPorIdOuEmail(
        @RequestParam(required = false) UUID id,
        @RequestParam(required = false) String email
        ){//FORMATA URL
        ResponseUsersDTO usuarioEncontradoPorEmail = userService.buscarUsuario(id, email);//DTO RECEBE
        return ResponseEntity.ok(usuarioEncontradoPorEmail);//200
    }
    //END POINT PARA DELETAR USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id){//PASSA O PARAMETRO DE BUSCA DO USUARIO
        userService.deletarUsuario(id);//CHAMA O METODO DELETAR DO SERVICE
        return ResponseEntity.noContent().build(); //RETORNA O STATUS 
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUsersDTO> atualizarUsuario(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateUsersDTO dadosNovos
    
    ){
        ResponseUsersDTO usuarioAtualizado = userService.atualizarUsuario(id, dadosNovos);
        return ResponseEntity.ok(usuarioAtualizado);
    }
}
