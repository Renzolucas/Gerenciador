package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.Task;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.CreateTaskBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repositories.TaskRepository;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repositories.UsersRepository;
@Service
public class TaskService {
    private final TaskRepository taskRepositoty;
    //PEGAMOS O USERSREPOSITORY PQ É LA QUE FICA O ID DO USUARIO
    private final UsersRepository usersRepository;
    public TaskService(TaskRepository taskRepositoty, UsersRepository usersRepository) {
        this.taskRepositoty = taskRepositoty;
        this.usersRepository = usersRepository;
    }
    
    public Task createTask(CreateTaskBodyDTO dto){
        //CRIA UM ESPAÇO NA ENTIDADE PARA AS NOVAS INFORMAÇÕES NO BANCO
        Task novaTask = new Task();

        //BUSCAR NO BANDO DE DADOS SE EXISTE OS IDs PELA COMUNICAO USERSREPOSITY
        Users employee = usersRepository.findById(dto.usersEmployeeId())
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + dto.usersEmployeeId()));
        Users admin = usersRepository.findById(dto.usersAdminId())
            .orElseThrow(() -> new RuntimeException("Admin não encontrado com o ID: " + dto.usersAdminId()));
        
        
        //DADOS DO DTO PARA A ENTIDADE
        novaTask.setTitulo(dto.titulo());
        novaTask.setDescricao(dto.descricao());
        novaTask.setUsersEmployee(employee);
        novaTask.setUsersAdmin(admin);
        novaTask.setDeadline(dto.deadline());
        novaTask.setCreatedAt(LocalDateTime.now());
        novaTask.setStatus(dto.status());
        novaTask.setPriority(dto.priority());

        

        //ATUALIZANDO O BANCO
        return taskRepositoty.save(novaTask);
    }
    
}