package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.Priority;
import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.Status;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record CreateTaskBodyDTO( 

    @NotBlank(message = "DIGITE UM TITULO VALIDO!!!")
    @Size(min = 2, max = 150, message = "O titulo deve ter entre 2 e 150 caracteres")
    String titulo, // TITULO DA TASK

    @NotBlank(message = "DIGITE UMA DESCRIÇÃO VALIDO!!!")
    @Size(min = 2, max = 1500, message = "A descrição deve ter entre 2 e 1500 caracteres")
    String descricao, // DESCRICAO
    
    @NotNull(message = "DIGITE UMA DATA VALIDA!!!")
    @Future(message = "A data de vencimento deve ser uma data futura")
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