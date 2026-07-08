package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;

import java.time.LocalDateTime;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

//RECORD PARA LEVAR AO POSTMAN AS INFORMAÇÕES DA BUSCA
public record ResponseListUsersDTO(
    //AQUI DECLARAMOS AS COISAS QUE VAO APARECER, (TUDO MENOS ID E PASSWORD)
    String name,
    String email,
    UsersRole role,
    LocalDateTime createdAtUser
) {}
