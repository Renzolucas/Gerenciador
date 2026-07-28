package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.exceptions;

public class GerenciadorConflictExceptions extends RuntimeException {
    public GerenciadorConflictExceptions(){super("Email já cadastrado");}
    public GerenciadorConflictExceptions(String message){super(message);}
}
