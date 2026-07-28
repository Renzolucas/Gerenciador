package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.infra;

import org.springframework.http.HttpStatus;


public class RestErrorMessage {
    private HttpStatus status;
    
    private String message;

    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;

        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
