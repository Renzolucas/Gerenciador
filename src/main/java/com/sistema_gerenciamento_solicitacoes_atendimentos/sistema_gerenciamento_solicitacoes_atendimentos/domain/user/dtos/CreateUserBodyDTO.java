package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;



import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserBodyDTO(
    //NOTBLANCK E NOTNULL SAO ANOTAÇÕES PARA NAO PERMITIR O USUARIO ENVIAR INFORMAÇÕES NULL

    @NotBlank(message = "Digite um nome Válido")
    String name, //NOME DO USUARIO
    @NotBlank(message = "Digite um Email Válido")
    @Email(message = "O formato do e-mail está inválido")
    String email, // SEU EMAIL
    @NotBlank(message = "Digite uma senha Válido")
    String password, // SUA SENHA
    @NotNull(message = "A função (role) é obrigatória")
    UsersRole role // SUA ROLE (ADMIN OU EMPLOYEE)
){}
