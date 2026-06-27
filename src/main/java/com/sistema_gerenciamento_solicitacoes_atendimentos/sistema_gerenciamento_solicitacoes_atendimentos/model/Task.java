package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.model;
import java.time.LocalDateTime;

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
    private Long id; // CPF DA TAREFA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //=================================================//
    @ManyToOne
    @JoinColumn(name = "employee_id")
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
    private LocalDateTime dataCriacao = LocalDateTime.now(); //DATA DE CRIAÇÃO DA TASK
    private LocalDateTime deadline; // DATA PRAZO
}
