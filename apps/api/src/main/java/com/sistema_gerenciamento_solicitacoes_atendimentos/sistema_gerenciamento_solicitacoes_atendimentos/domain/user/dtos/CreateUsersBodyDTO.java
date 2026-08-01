package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos;



import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUsersBodyDTO(
    //NOTBLANCK E NOTNULL SAO ANOTAÇÕES PARA NAO PERMITIR O USUARIO ENVIAR INFORMAÇÕES NULL

    @NotBlank(message = "Digite um nome Válido")
    String name, //NOME DO USUARIO

    @NotBlank(message = "Digite um Email Válido")
    @Email(message = "O formato do e-mail está inválido")
    @Size(min = 2, max = 100, message = "O e-mail deve ter entre 2 e 100 caracteres")
    String email, // SEU EMAIL

    @NotBlank(message = "Digite uma senha Válido")
    @Size(min = 5, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    String password, // SUA SENHA

    @NotNull(message = "A função (role) é obrigatória")
    UsersRole role // SUA ROLE (ADMIN OU EMPLOYEE)
){}
