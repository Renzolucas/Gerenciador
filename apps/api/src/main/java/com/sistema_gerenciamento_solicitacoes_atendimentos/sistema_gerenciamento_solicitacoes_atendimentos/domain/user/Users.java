    package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.UsersRole;
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
    import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

    @Entity //DIZ AO BANCO COM AJUDA DO SPRING (TUDO AQUI E TABELA)
    @Table(name = "users")
    public class Users {
        /* 
        NAME
        ROLE(ENUM - EMPLOYEE OR ADMIN)
        EMAIL
        PASSWORD
    */
        @Id
        @GeneratedValue(strategy = GenerationType.UUID) //DIZ AO SPRING QUE SE Long Id for null entao pegar o id anterior e ++
        private UUID id; // ID DO USUARIO

        //VALIDAÇÃO DE NOME
        @Column(nullable = false)
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        private String name; // NOME DO USUARIO

        //VALIDAÇÃO DE EMAIL
        @Column(nullable = false, unique = true)
        @Email(message = "O formato do e-mail está inválido")
        private String email; // EMAIL DO USUARIO

        //VALIDAÇÃO DE SENHA
        @Column(nullable = false)
        private String password; // SENHA DO USUARIO

        //VALIDAÇÃO DE ROLE
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private UsersRole role; // CATEGORIA DO USUARIO (ADMIN OR EMPLOYEE)
        
        //VALIDAÇÃO DE DATA DE CRIAÇÃO
        @CreationTimestamp
        private LocalDateTime createdAtUser; //DATA DE CRIAÇÃO DO USUARIO

        //VALIDAÇÃO DE DATA DE ATUALIZAÇÃO
        @UpdateTimestamp
        private LocalDateTime updateAtUser; //DATA DE ATUALIZAÇÃO
        
        //LISTA DE TASK QUE O USER TEM
        @OneToMany(mappedBy = "usersEmployee")
        @JsonIgnore
        private List<Task> tarefasEmployee;
        @OneToMany(mappedBy = "usersAdmin")
        @JsonIgnore
        private List<Task> tarefasAdmin;

        //PARA O SERVICE
        public Users() {
        }
        
        // CONSTRUCT NORMAL
        public Users(String name, String email, String password, UsersRole role, LocalDateTime createdAtUser,
        LocalDateTime updateAtUser, List<Task> tarefasEmployee, List<Task> tarefasAdmin) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
            this.createdAtUser = createdAtUser;
            this.updateAtUser = updateAtUser;
            this.tarefasEmployee = tarefasEmployee;
            this.tarefasAdmin = tarefasAdmin;
        }

        //GET E SET
        public UUID getId() {
            return id;
        }
        
        
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
        public UsersRole getRole() {
            return role;
        }
        public LocalDateTime getCreatedAtUser() {
            return createdAtUser;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public void setRole(UsersRole role) {
            this.role = role;
        }
        public void setCreatedAtUser(LocalDateTime createdAtUser) {
            this.createdAtUser = createdAtUser;
        }
        public List<Task> getTarefasEmployee() {
            return tarefasEmployee;
        }
        public void setTarefasEmployee(List<Task> tarefasEmployee) {
            this.tarefasEmployee = tarefasEmployee;
        }
        public List<Task> getTarefasAdmin() {
            return tarefasAdmin;
        }
        public void setTarefasAdmin(List<Task> tarefasAdmin) {
            this.tarefasAdmin = tarefasAdmin;
        }

        public LocalDateTime getUpdateAtUser() {
            return updateAtUser;
        }

        public void setUpdateAtUser(LocalDateTime updateAtUser) {
            this.updateAtUser = updateAtUser;
        }
        
    }
