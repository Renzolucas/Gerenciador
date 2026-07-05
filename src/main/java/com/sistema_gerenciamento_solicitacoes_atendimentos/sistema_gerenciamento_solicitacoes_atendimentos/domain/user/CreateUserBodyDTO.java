package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user;



import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.enums.UsersRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserBodyDTO {
    //NOTBLANCK E NOTNULL SAO ANOTAÇÕES PARA NAO PERMITIR O USUARIO ENVIAR INFORMAÇÕES NULL

    @NotBlank(message = "Digite um nome Válido")
    private String name; //NOME DO USUARIO
    @NotBlank(message = "Digite um Email Válido")
    @Email(message = "O formato do e-mail está inválido")
    private String email; // SEU EMAIL
    @NotBlank(message = "Digite uma senha Válido")
    private String password; // SUA SENHA
    @NotNull(message = "A função (role) é obrigatória")
    private UsersRole role; // SUA ROLE (ADMIN OU EMPLOYEE)

    //================GET E SET====================//
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UsersRole getRole() {
        return role;
    }
    public void setRole(UsersRole role) {
        this.role = role;
    }
}
