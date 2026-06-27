package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //DIZ AO BANCO COM AJUDA DO SPRING (TUDO AQUI E TABELA)
public class Users {
    /* 
    NAME
    ROLE(ENUM - EMPLOYEE OR ADMIN)
    EMAIL
    PASSWORD
   */
    //========================================================================================//
    
    private String name; // NOME DO USUARIO
    @Id //DIZ AO SPRING QUE ISSO VAI SER UM "CPF" OR VALOR UNICO
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DIZ AO SPRING QUE SE Long Id for null entao pegar o id anterior e ++
    private Long id; // CPF DO USUARIO
    //=======================================================================================//
    private String email; // EMAIL DO USUARIO
    private String password; // SENHA DO USUARIO
    @Enumerated(EnumType.STRING)
    private UsersRole userRole; // CATEGORIA DO USUARIO (ADM OR EMPLOYEE)
}
