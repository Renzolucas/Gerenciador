package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.controllers;



import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.Task;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.CreateTaskBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.ResponseTaskDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.ResponseTaskDTO;
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
    public ResponseEntity<ResponseTaskDTO> createTask(@Valid @RequestBody CreateTaskBodyDTO dto){
        ResponseTaskDTO createdTask = taskService.createTask(dto);
        return ResponseEntity.ok(createdTask);
    }
    @GetMapping("/listagem")//BUSCAR TODAS AS TAREFAS, SENSIVELS COM OS DADOS
    public ResponseEntity<List<ResponseTaskDTO>> listagemTask(){
        //CHAMA O METODO LOGICO
        List <ResponseTaskDTO> novoDTO = taskService.buscarTask().stream().map(task -> new ResponseTaskDTO(
            task.getTitulo(),
            task.getDescricao(),
            task.getDeadline(),
            task.getStatus(),
            task.getPriority()
        )).collect(Collectors.toList());// COLECT COLETA E TRANSFORMA SEU TIPO
        //RETORNA STATUS
        return ResponseEntity.ok(novoDTO);
    }
    //ROTA PARA BUSCAR UMA TASK POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskDTO> buscarPorId(@PathVariable UUID id){
        ResponseTaskDTO buscarPorIdEncontrada = taskService.buscarTaskPorId(id);
        return ResponseEntity.ok(buscarPorIdEncontrada);
    }
    //ROTA PARA DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable UUID id){
        taskService.deletarTask(id);
        return ResponseEntity.noContent().build();
    }
    //ROTA PARA ATUALIZAR TASK
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTaskDTO> atualizarTarefa(
        @PathVariable UUID id,
        @RequestBody @Valid ResponseTaskDTO dadosNovos
    ){
        Task task = taskService.atualizarTask(id, dadosNovos);
        ResponseTaskDTO novoDTO;
        novoDTO = new ResponseTaskDTO(
            task.getTitulo(),
            task.getDescricao(),
            task.getDeadline(),
            task.getStatus(),
            task.getPriority()
        );
        return ResponseEntity.ok(novoDTO);
    }
}
