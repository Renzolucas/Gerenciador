package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task;
import java.time.LocalDateTime;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.enums.Priority;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.enums.Status;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Task {
    /*
    USER_ID(ADM)
    USER_ID(EMPLOYEE)
    STATUS(ENUM Solicitação aberta; Solicitação em atendimento; Solicitação finalizada.)
    PRIORITY(ENUM ALTA, MEDIA E BAIXA)
    TITULO
    DATA DE CRIAÇÃO
    DESCRICAO
    DEADLINE/prazo
    */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // ID da Task
    //=================================================//
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Users usersEmployee; // NOME DO EMPLOYEE
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Users usersAdmin; //NOME DO ADMIN
    //===============================================//
    @Enumerated(EnumType.STRING)
    private Status status; // DEFINIR O STATUS (ENUM COM 3 OPCAO)
    @Enumerated(EnumType.STRING)
    private Priority priority; // DEFINIR A PRIORIDADE (BAIXA,MEDIA, ALTA)
    //==============================================//
    private String titulo; // TITULO DA TASK
    private String descricao; // DESCRIÇÃO DA TASK
    private LocalDateTime createdAt = LocalDateTime.now(); //DATA DE CRIAÇÃO DA TASK
    private LocalDateTime updatedAt; //DATA DE EDIÇÃO DA TASK
    private LocalDateTime deadline; // DATA PRAZO
    
    //=================CONSTRUCTOR====================//
    public Task(UUID id, Users usersEmployee, Users usersAdmin, Status status, Priority priority, String titulo,
            String descricao, LocalDateTime updatedAt, LocalDateTime deadline) {
        this.id = id;
        this.usersEmployee = usersEmployee;
        this.usersAdmin = usersAdmin;
        this.status = status;
        this.priority = priority;
        this.titulo = titulo;
        this.descricao = descricao;
        this.updatedAt = updatedAt;
        this.deadline = deadline;
    }
    
    // PARA O SERVICE
    public Task() {
    }


    public Users getUsersEmployee() {
        return usersEmployee;
    }
    public void setUsersEmployee(Users usersEmployee) {
        this.usersEmployee = usersEmployee;
    }
    public Users getUsersAdmin() {
        return usersAdmin;
    }
    public void setUsersAdmin(Users usersAdmin) {
        this.usersAdmin = usersAdmin;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public UUID getId() {
        return id;
    }
    
}
