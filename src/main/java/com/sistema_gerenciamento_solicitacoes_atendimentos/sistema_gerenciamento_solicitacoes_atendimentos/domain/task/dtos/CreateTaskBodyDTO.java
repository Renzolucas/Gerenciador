package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos;

import java.util.UUID;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.Priority;
import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
public record CreateTaskBodyDTO( 
    @NotBlank(message = "DIGITE UM TITULO VALIDO!!!")
    String titulo, // TITULO DA TASK
    @NotBlank(message = "DIGITE UMA DESCRIÇÃO VALIDO!!!")
    String descricao, // DESCRICAO
    LocalDateTime deadline, // DATA PRAZO
    @NotNull(message = "DIGITE UM ID VALIDO!!!")
    UUID usersEmployeeId, //USUARIO QUE VAI FAZER TASK
    @NotNull(message = "DIGITE UM ID VALIDO!!!")
    UUID usersAdminId, // USUARIO QUEM MANDOU A TASK
    @NotNull(message = "A função (STATUS) é obrigatória")
    Status status, //STATUS DA TASK
    @NotNull(message = "A função (PRIORIDADE) é obrigatória")
    Priority priority // PRIORIDADE
){}