package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

//RECORD PARA LEVAR AO POSTMAN AS INFORMAÇÕES DA BUSCA
public record ResponseUsersDTO(
    UUID id,
    String name,
    String email,
    UsersRole role,
    LocalDateTime createdAtUser,
    LocalDateTime updateAtUser
) {}
