package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task;

import java.util.UUID;
import java.time.LocalDateTime;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.enums.Status;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.enums.Priority;


public class CreateTaskBodyDTO {
    private String titulo; // TITULO DA TASK
    private String descricao; // DESCRICAO

    private LocalDateTime deadline; // DATA PRAZO

    private UUID usersEmployeeId; //USUARIO QUE VAI FAZER TASK
    private UUID usersAdminId; // USUARIO QUEM MANDOU A TASK
    
    private Status status; //STATUS DA TASK
    private Priority priority; // PRIORIDADE

    //GET E SET
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
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public UUID getUsersEmployeeId() {
        return usersEmployeeId;
    }
    public void setUsersEmployeeId(UUID usersEmployeeId) {
        this.usersEmployeeId = usersEmployeeId;
    }
    public UUID getUsersAdminId() {
        return usersAdminId;
    }
    public void setUsersAdminId(UUID usersAdminId) {
        this.usersAdminId = usersAdminId;
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
}
