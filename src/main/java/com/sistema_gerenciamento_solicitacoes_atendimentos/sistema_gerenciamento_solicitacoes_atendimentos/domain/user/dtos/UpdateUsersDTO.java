package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUsersDTO(
    @NotBlank(message = "Digite um nome Válido")
    String name,

    @NotBlank(message = "Digite um Email Válido")
    @Email(message = "O formato do e-mail está inválido")
    String email,
    
    @NotNull(message = "A função (role) é obrigatória")
    UsersRole role
    
) {}
