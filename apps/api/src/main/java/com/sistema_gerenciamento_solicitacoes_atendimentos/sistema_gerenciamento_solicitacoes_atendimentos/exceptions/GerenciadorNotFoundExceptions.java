package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.exceptions;

public class GerenciadorNotFoundExceptions extends RuntimeException{
    public GerenciadorNotFoundExceptions(){super("ID NÃO ENCONTRADO");}
    public GerenciadorNotFoundExceptions(String message){super(message);}
}
