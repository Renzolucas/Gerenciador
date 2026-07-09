package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

public record ResponseSearchEmailID(
    UUID id,
    String name,
    String email,
    UsersRole role,
    LocalDateTime createdAtUser
) {}
