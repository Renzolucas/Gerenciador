package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos;

import java.time.LocalDateTime;

import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.Priority;
import com.sistema_gerenciamento_solicitacoes_atendimentos.enums.Status;


public record ResponseTaskDTO(
    String titulo, // TITULO DA TASK
    String descricao, // DESCRICAO
    LocalDateTime deadline, // DATA PRAZO
    Status status, //STATUS DA TASK
    Priority priority // PRIORIDADE
){} 
