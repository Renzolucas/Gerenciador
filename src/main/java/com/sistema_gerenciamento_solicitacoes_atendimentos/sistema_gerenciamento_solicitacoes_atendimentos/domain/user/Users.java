package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user;

import java.util.List;
import java.util.UUID;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.enums.UsersRole;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //DIZ AO BANCO COM AJUDA DO SPRING (TUDO AQUI E TABELA)
@Table(name = "users")
public class Users {
    /* 
    NAME
    ROLE(ENUM - EMPLOYEE OR ADMIN)
    EMAIL
    PASSWORD
   */
    //========================================================================================//
    
    @Id //DIZ AO SPRING QUE ISSO VAI SER UM "ID" OR VALOR UNICO
    @GeneratedValue(strategy = GenerationType.UUID) //DIZ AO SPRING QUE SE Long Id for null entao pegar o id anterior e ++
    private UUID id; // ID DO USUARIO
    //=======================================================================================//
    @Column(nullable = false)
    private String name; // NOME DO USUARIO
    @Column(nullable = false, unique = true)
    private String email; // EMAIL DO USUARIO
    @Column(nullable = false)
    private String password; // SENHA DO USUARIO
    @Enumerated(EnumType.STRING)
    private UsersRole role; // CATEGORIA DO USUARIO (ADMIN OR EMPLOYEE)
    /* @OneToMany()
    private List<Task> tasks; */
}
