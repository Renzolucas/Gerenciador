package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.CreateTaskBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.ResponseTaskBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public ResponseEntity<ResponseTaskBodyDTO> createTask(@Valid @RequestBody CreateTaskBodyDTO dto){
        ResponseTaskBodyDTO createdTask = taskService.createTask(dto);
        return ResponseEntity.ok(createdTask);
    }
    @GetMapping("/listagem")//BUSCAR TODAS AS TAREFAS, SENSIVEL COM OS DADOS
    public ResponseEntity<List<ResponseTaskBodyDTO>> listagemTask(){
        //CHAMA O METODO LOGICO
        List<ResponseTaskBodyDTO> listagemLimpa = taskService.buscarTask();
        //RETORNA STATUS
        return ResponseEntity.ok(listagemLimpa);
    }
    //ROTA PARA BUSCAR UMA TASK POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskBodyDTO> buscarPorId(@PathVariable UUID id){
        ResponseTaskBodyDTO buscarPorIdEncontrada = taskService.buscarTaskPorId(id);
        return ResponseEntity.ok(buscarPorIdEncontrada);
    }
    //ROTA PARA DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable UUID id){
        taskService.deletarTask(id);
        return ResponseEntity.noContent().build();
    }
    
}
