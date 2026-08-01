package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.exceptions.GerenciadorConflictExceptions;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.exceptions.GerenciadorNotFoundExceptions;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GerenciadorNotFoundExceptions.class)
    private ResponseEntity<RestErrorMessage> gerenciadorNotFoundException(GerenciadorNotFoundExceptions exception) {
        RestErrorMessage mensagemErro = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
    }
    @ExceptionHandler(GerenciadorConflictExceptions.class)
    private ResponseEntity<RestErrorMessage> gerenciadorConflictException(GerenciadorConflictExceptions exception) {
        RestErrorMessage mensagemErro = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagemErro);
    }
}
