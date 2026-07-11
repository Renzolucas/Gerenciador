package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

public record UpdateUsersDTO(
    String name,
    String email,
    UsersRole role
    
) {}
